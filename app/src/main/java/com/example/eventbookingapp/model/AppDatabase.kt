package com.example.eventbookingapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eventbookingapp.model.dao.EventDao
import com.example.eventbookingapp.model.dto.event.EventListRoomEntity

@Database(entities = [EventListRoomEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}