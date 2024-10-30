package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.EventDao
import com.example.data.dto.event.EventListRoomEntity
import com.example.data.dto.event.EventTypeConverters

@TypeConverters(EventTypeConverters::class)
@Database(entities = [EventListRoomEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}