package com.example.domain.entities.event

data class EventDetailEntity(
    val capacity: Int,
    val date: String,
    val latitude: Double,
    val locationName: String,
    val longitude: Double,
    val name: String,
    val participants: Int
)
