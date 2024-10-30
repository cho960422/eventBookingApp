package com.example.data.dto.event

import com.google.gson.annotations.SerializedName

data class EventDetailDto(
    @SerializedName("id") val id: String,
    @SerializedName("capacity") val capacity: Int,
    @SerializedName("date") val date: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("locationName") val locationName: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("name") val name: String,
    @SerializedName("participants") val participants: Int,
    @SerializedName("author") val author: EventUserDto,
    @SerializedName("content") val content: String,
)