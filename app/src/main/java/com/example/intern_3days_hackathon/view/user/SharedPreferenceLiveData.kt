package com.example.intern_3days_hackathon.view.user

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

fun SharedPreferences.liveString(key: String, defaultValue: String): SharedPreferenceLiveData<String?> {
    return SharedPreferenceLiveData(this, key, defaultValue, SharedPreferences::getString)
}

class SharedPreferenceLiveData<T>(
        private val prefs: SharedPreferences,
        private val key: String,
        private val defaultValue: T,
        private val getter: (SharedPreferences).(String, T) -> T
) : LiveData<T>() {

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == this.key) {
            value = prefs.getter(key, defaultValue)
        }
    }

    override fun onActive() {
        super.onActive()
        value = prefs.getter(key, defaultValue)
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        prefs.unregisterOnSharedPreferenceChangeListener(listener)
    }

}