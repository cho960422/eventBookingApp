package com.example.eventbookingapp.model.mapper

import com.example.eventbookingapp.model.dto.event.EventDetailDto
import com.example.eventbookingapp.model.dto.event.EventWriteDto
import com.example.eventbookingapp.view.entities.event.EventDetailEntity
import com.example.eventbookingapp.view.entities.event.EventWriteRequestEntity

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
}