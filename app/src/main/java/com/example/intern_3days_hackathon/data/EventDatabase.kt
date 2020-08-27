package com.example.intern_3days_hackathon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedEvent::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract val savedEventDao: SavedEventDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // インスタンス化されていない場合にビルダーを起動
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            EventDatabase::class.java,
                            "event_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}