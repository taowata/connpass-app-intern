package com.example.intern_3days_hackathon.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.intern_3days_hackathon.data.SavedEventDao

class SavedEventViewModelFactory(
        private val dataSource: SavedEventDao,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedEventViewModel::class.java)) {
            return SavedEventViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}