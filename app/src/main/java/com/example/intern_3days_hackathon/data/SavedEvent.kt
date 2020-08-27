package com.example.intern_3days_hackathon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_event_table")
data class SavedEvent(
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "date") var date: String = "",
        @ColumnInfo(name = "location") var location: String = "",
        @ColumnInfo(name = "event_url") var url: String = "",
        @ColumnInfo(name = "has_finished") var hasFinished: Boolean = false,
        @ColumnInfo(name = "image_url") var imageUrl: String = "",
        @PrimaryKey(autoGenerate = true) val eventId: Int = 0
)