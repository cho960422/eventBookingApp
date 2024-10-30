package com.example.domain.entities.event

import java.time.LocalDateTime

data class EventListEntity(
    val id: String,
    val author: UserEntity,
    val content: String,
    val location: EventLocationEntity,
    val date: LocalDateTime,
    val createAt: LocalDateTime,
    val capacity: Int, // 정원
    val participants: Int, // 현재 참여자 수
    val bookMarkFlag: Boolean,
    val joinFlag: Boolean
)
