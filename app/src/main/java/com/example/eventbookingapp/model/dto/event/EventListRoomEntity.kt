package com.example.eventbookingapp.model.dto.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eventbookingapp.view.entities.event.EventLocationEntity
import com.example.eventbookingapp.view.entities.event.UserEntity
import java.time.LocalDateTime

@Entity(tableName = "events")
data class EventListRoomEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "author") val author: UserEntity,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "location") val location: EventLocationEntity,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "capacity") val capacity: Int, // 정원
    @ColumnInfo(name = "participants") val participants: Int, // 현재 참여자 수
    @ColumnInfo(name = "bookMarkFlag") val bookMarkFlag: Boolean,
    @ColumnInfo(name = "joinFlag") val joinFlag: Boolean
)
