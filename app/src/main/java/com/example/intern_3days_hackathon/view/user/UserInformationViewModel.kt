package com.example.intern_3days_hackathon.view.user

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class UserInformationViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val PREF = "user_information"
        const val KEY_USER_NAME = "username"
        const val KEY_FAV_WORD1 = "fav_word1"
        const val KEY_FAV_WORD2 = "fav_word2"
        const val KEY_FAV_WORD3 = "fav_word3"
        const val KEY_MY_PAGE_URL = "my_page_url"
    }

    private val prefs = application.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    val userName = prefs.liveString(KEY_USER_NAME, "tao")
    val favWord1 = prefs.liveString(KEY_FAV_WORD1, "Kotlin")
    val favWord2 = prefs.liveString(KEY_FAV_WORD2, "Android")
    val favWord3 = prefs.liveString(KEY_FAV_WORD3, "Python")
    val myPageUrl = prefs.liveString(KEY_MY_PAGE_URL, "https://connpass.com/user/Tao130/")
}
