package com.example.data.dto.event

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.example.domain.entities.event.EventLocationEntity
import com.example.domain.entities.event.UserEntity
import com.google.gson.Gson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class EventTypeConverters {
    @TypeConverter
    fun fromUserEntity(author: UserEntity): String {
        return Gson().toJson(author)
    }

    @TypeConverter
    fun toUserEntity(author: String): UserEntity {
        return Gson().fromJson(author, UserEntity::class.java)
    }

    @TypeConverter
    fun fromEventLocationEntity(location: EventLocationEntity): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun toLocationEntity(locationStr: String): EventLocationEntity {
        return Gson().fromJson(locationStr, EventLocationEntity::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): Long {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(timeStamp: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault())
    }
}