package com.sims.roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sims.roomdbdemo.databinding.ActivityMainBinding
import com.sims.roomdbdemo.db.SubscriberDatabase
import com.sims.roomdbdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var subscriberViewModelFactory: SubscriberViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val subscriberDAO = SubscriberDatabase.getInstance(applicationContext).subscriberDAO
        val repository = SubscriberRepository(subscriberDAO)
        val subscriberViewModelFactory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, subscriberViewModelFactory).get(SubscriberViewModel::class.java)
        binding.mySubscriberViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MyTag", it.toString())
            binding.subscriberRecyclerView.adapter = SubscriberRecyclerViewAdapter(it)
        })
    }
}