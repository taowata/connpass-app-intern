package com.example.intern_3days_hackathon.view.save

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.intern_3days_hackathon.data.SavedEvent
import com.example.intern_3days_hackathon.data.SavedEventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedEventViewModel(
        private val savedEventDao: SavedEventDao,
        application: Application) : AndroidViewModel(application) {

    val eventList: LiveData<List<SavedEvent>> = savedEventDao.getAllEvent()

    fun insertEvent(event: SavedEvent) = viewModelScope.launch(Dispatchers.IO) {
        savedEventDao.insert(event)
    }

    fun deleteEvent(event: SavedEvent) = viewModelScope.launch(Dispatchers.IO) {
        savedEventDao.delete(event)
    }

}