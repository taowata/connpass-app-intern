package com.example.intern_3days_hackathon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedEventDao {
    @Insert
    fun insert(event: SavedEvent)

    @Query("select * from saved_event_table order by date asc")
    fun getAllEvent(): LiveData<List<SavedEvent>>

}