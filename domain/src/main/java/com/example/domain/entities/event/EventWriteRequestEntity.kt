package com.example.domain.entities.event

// TODO() 비즈니스 로직에 맞게 커스텀하기

data class EventWriteRequestEntity(
    val name: String,
    val categoryName: String,
    val locationName: String,
    val latitude: Double,
    val longitude: Double,
    val date: String,
    val maxParticipants: Int,
    val content: String
)