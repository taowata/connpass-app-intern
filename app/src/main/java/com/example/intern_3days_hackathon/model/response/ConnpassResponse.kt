package com.example.intern_3days_hackathon.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConnpassResponse(
        @SerializedName("results_start")
        val resultsStart: Long,

        @SerializedName("results_returned")
        val resultsReturned: Long,

        @SerializedName("results_available")
        val resultsAvailable: Long,

        val events: List<Event>
)

@Serializable
data class Event(
        @SerializedName("event_id")
        val eventID: Long,

        val title: String,
        val catch: String,
        val description: String,

        @SerializedName("event_url")
        val eventURL: String,

        @SerializedName("started_at")
        val startedAt: String,

        @SerializedName("ended_at")
        val endedAt: String,

        val limit: Long? = null,

        @SerializedName("hash_tag")
        val hashTag: String,

        @SerializedName("event_type")
        val eventType: String,

        val accepted: Long,
        val waiting: Long,

        @SerializedName("updated_at")
        val updatedAt: String,

        @SerializedName("owner_id")
        val ownerID: Long,

        @SerializedName("owner_nickname")
        val ownerNickname: String,

        @SerializedName("owner_display_name")
        val ownerDisplayName: String,

        val place: String?,
        val address: String?,
        val lat: String?,
        val lon: String?,
        val series: Series
)

@Serializable
data class Series(
        val id: Long,
        val title: String,
        val url: String
)
