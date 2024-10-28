package com.example.eventbookingapp.domain.entities.event

import android.location.Location
import kotlinx.serialization.Serializable

@Serializable
data class EventLocationEntity(
    val latitude: Double,
    val longitude: Double,
    val name: String
)
