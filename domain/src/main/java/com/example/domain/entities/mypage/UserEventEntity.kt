package com.example.domain.entities.mypage

import java.time.LocalDateTime

data class UserEventEntity(
    val applicants: Int,
    val author: String,
    val bookmarkId: Int,
    val capacity: Int,
    val eventDate: LocalDateTime,
    val eventName: String
)
