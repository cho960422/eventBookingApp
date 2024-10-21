package com.example.eventbookingapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eventbookingapp.model.dao.EventDao
import com.example.eventbookingapp.model.dto.event.EventListRoomEntity
import com.example.eventbookingapp.model.dto.event.EventTypeConverters

@TypeConverters(EventTypeConverters::class)
@Database(entities = [EventListRoomEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}