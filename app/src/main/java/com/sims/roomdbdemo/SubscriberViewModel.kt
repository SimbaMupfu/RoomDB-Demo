package com.sims.roomdbdemo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sims.roomdbdemo.db.Subscriber
import com.sims.roomdbdemo.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private var statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)
        } else {
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(Subscriber(0, name, email))
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    private fun insert(subscriber: Subscriber) = viewModelScope.launch {
        val newRowID = repository.insert(subscriber)
        if (newRowID > -1) {
            statusMessage.value = Event("Subscriber inserted successfully $newRowID")
        } else {
            statusMessage.value = Event("There was an error in inserting the new subscriber")
        }

    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch {
        val numberOfRowsUpdated = repository.update(subscriber)
        if(numberOfRowsUpdated > 0){
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$numberOfRowsUpdated row(s) updated successfully")
        }else{
            statusMessage.value = Event("There was an error in updating the subscriber info")
        }
    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch {
        val numberOfDeletedRows = repository.delete(subscriber)
        if(numberOfDeletedRows > 0){
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$numberOfDeletedRows subscriber(s) deleted successfully")
        }else{
            statusMessage.value = Event("Subscriber could not be deleted")
        }
    }

    private fun clearAll() = viewModelScope.launch {
        val numberOfDeletedRows = repository.deleteAll()
        if(numberOfDeletedRows > 0){
            statusMessage.value = Event("All $numberOfDeletedRows subscribers deleted successfully")
        }else{
            statusMessage.value = Event("Subscribers could not be deleted")
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}