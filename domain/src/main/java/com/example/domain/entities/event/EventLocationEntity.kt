package com.example.domain.entities.event

import kotlinx.serialization.Serializable

@Serializable
data class EventLocationEntity(
    val latitude: Double,
    val longitude: Double,
    val name: String
)
