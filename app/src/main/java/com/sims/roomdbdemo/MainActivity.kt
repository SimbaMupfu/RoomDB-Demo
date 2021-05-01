package com.sims.roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import com.sims.roomdbdemo.databinding.ActivityMainBinding
import com.sims.roomdbdemo.db.Subscriber
import com.sims.roomdbdemo.db.SubscriberDatabase
import com.sims.roomdbdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var subscriberViewModelFactory: SubscriberViewModelFactory

    private lateinit var adapter: SubscriberRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val subscriberDAO = SubscriberDatabase.getInstance(applicationContext).subscriberDAO
        val repository = SubscriberRepository(subscriberDAO)
        subscriberViewModelFactory = SubscriberViewModelFactory(repository)
        subscriberViewModel =
            ViewModelProvider(this, subscriberViewModelFactory).get(SubscriberViewModel::class.java)
        binding.mySubscriberViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SubscriberRecyclerViewAdapter { selectedItem: Subscriber ->
            listItemClicked(selectedItem)
        }
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MyTag", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber) {
//        Toast.makeText(this, "Selected name is ${subscriber.name}", Toast.LENGTH_SHORT).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}