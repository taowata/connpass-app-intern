package com.example.intern_3days_hackathon.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.intern_3days_hackathon.HttpClient
import com.example.intern_3days_hackathon.model.response.ConnpassResponse
import com.example.intern_3days_hackathon.model.response.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EventListRepository {
    private val LOG_TAG = EventListRepository::class.java.simpleName
    fun listArticle(count: Int, query: String?): LiveData<ArrayList<Event>> {
        val data = MutableLiveData<ArrayList<Event>>()
        val connpassService = HttpClient.connpassService
        connpassService.getEvents(count, query).enqueue(object : Callback<ConnpassResponse> {
            override fun onResponse(call: Call<ConnpassResponse>?, response: Response<ConnpassResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.postValue(it.events as ArrayList<Event>)
                    }
                } else {
                    data.postValue(null)
                }
            }

            override fun onFailure(call: Call<ConnpassResponse>?, t: Throwable) {
                data.postValue(null)
                Log.e(LOG_TAG, "API連携に失敗:$t")
            }
        })
        return data
    }
}
