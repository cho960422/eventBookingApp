package com.example.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.dto.event.EventDetailDto
import com.example.data.dto.event.EventListRoomEntity
import com.example.data.dto.event.EventUserDto
import com.example.data.dto.event.EventWriteDto
import com.example.domain.entities.event.EventDetailEntity
import com.example.domain.entities.event.EventLocationEntity
import com.example.domain.entities.event.EventWriteRequestEntity
import com.example.domain.entities.event.UserEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object EventMapper {
    fun convertEventWriteRequestToDto(entity: EventWriteRequestEntity): EventWriteDto {
        return EventWriteDto(
            entity.name,
            entity.categoryName,
            entity.locationName,
            entity.latitude,
            entity.longitude,
            entity.date,
            entity.maxParticipants,
            entity.content
        )
    }

    fun eventDetailDtoToEntity(dto: EventDetailDto): EventDetailEntity = with(dto) {
        return EventDetailEntity(
            capacity,
            date,
            latitude,
            locationName,
            longitude,
            name,
            participants
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eventDetailDtoToListRoomEntity(dto: EventDetailDto): EventListRoomEntity = with(dto) {
        return EventListRoomEntity(
            id = "",
            author = eventUserDtoToEntity(author),
            content = content,
            location = EventLocationEntity(
                latitude, longitude, locationName
            ),
            date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            capacity = capacity,
            participants = participants,
            false,
            false
        )
    }

    private fun contentDtoToEntity(content: String) :String {
        return content
    }

    fun eventUserDtoToEntity(user: EventUserDto) : UserEntity {
        return UserEntity(
            user.id,
            user.nickname
        )
    }
}