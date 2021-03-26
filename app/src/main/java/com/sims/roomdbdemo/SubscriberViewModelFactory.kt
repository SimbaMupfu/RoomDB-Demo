package com.sims.roomdbdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sims.roomdbdemo.db.SubscriberRepository
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val subscriberRepository: SubscriberRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(subscriberRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}