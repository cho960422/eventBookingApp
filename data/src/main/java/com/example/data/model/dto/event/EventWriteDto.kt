package com.example.data.model.dto.event

import com.google.gson.annotations.SerializedName

data class EventWriteDto(
    @SerializedName("name") val name: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("locationName") val locationName: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("date") val date: String,
    @SerializedName("maxParticipants") val maxParticipants: Int,
    @SerializedName("content") val content: String
)