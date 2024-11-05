package com.example.data.model.dto.mypage

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserEventDto(
    @SerializedName("applicants")
    val applicants: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("bookmarkId")
    val bookmarkId: Int,
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("eventDate")
    val eventDate: String,
    @SerializedName("eventName")
    val eventName: String
)