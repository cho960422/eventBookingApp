package com.example.eventbookingapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eventbookingapp.data.dto.event.EventListRoomEntity

@Dao
interface EventDao {
    @Insert
    suspend fun insertAll(vararg events: EventListRoomEntity)

    @Query("DELETE FROM events")
    suspend fun deleteAll()

    @Query("SELECT * FROM events WHERE content LIKE :query LIMIT 30 OFFSET :start")
    suspend fun getEventList(query: String, start: Int): List<EventListRoomEntity>
}