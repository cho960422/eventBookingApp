package com.example.eventbookingapp.model.dto.base

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class BaseResponse<T>(
    @SerializedName("data") val data: T? = null,
    @SerializedName("errorCode") val errorCode: Int? = null,
    @SerializedName("message") val message: String? = null
)
