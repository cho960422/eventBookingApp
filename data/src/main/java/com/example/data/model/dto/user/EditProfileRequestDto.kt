package com.example.data.model.dto.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EditProfileRequestDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("local_name")
    val localName: String,
    @SerializedName("birth")
    val birth: String,
    @SerializedName("character")
    val character: String
)
