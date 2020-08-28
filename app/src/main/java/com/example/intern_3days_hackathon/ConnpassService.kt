package com.example.intern_3days_hackathon

import com.example.intern_3days_hackathon.model.ConnpassResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConnpassService {
    @GET("event")
    @Headers("Content-Type: application/json")
    fun getEvents(@Query("count") count: Int,
                  @Query("keyword") query: String?): Call<ConnpassResponse>
}
