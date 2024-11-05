package com.example.data.model.dto.mypage

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageDto(
    @SerializedName("birth")
    val birth: String,
    @SerializedName("bookmarkList")
    val bookmarkList: List<UserEventDto>,
    @SerializedName("character")
    val character: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("userEventList")
    val userEventList: List<UserEventDto>
)