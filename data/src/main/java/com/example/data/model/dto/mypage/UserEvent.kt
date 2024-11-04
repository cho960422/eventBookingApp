package com.example.data.model.dto.mypage

data class UserEvent(
    val applicants: Int,
    val author: String,
    val bookmarkId: Int,
    val capacity: Int,
    val eventDate: String,
    val eventName: String
)