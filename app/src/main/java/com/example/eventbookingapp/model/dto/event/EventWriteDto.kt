package com.example.eventbookingapp.model.dto.event

import com.example.eventbookingapp.model.dto.base.BaseResponse
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

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