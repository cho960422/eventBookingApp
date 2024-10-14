package com.example.eventbookingapp.model.dto.event

import com.google.gson.annotations.SerializedName

data class EventDetailDto(
    @SerializedName("capacity") val capacity: Int,
    @SerializedName("date") val date: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("locationName") val locationName: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("name") val name: String,
    @SerializedName("participants") val participants: Int
)