package com.example.domain.entities.mypage

import java.time.LocalDate

data class MyPageEntity(
    val birth: LocalDate,
    val bookmarkList: List<MyPageEntity>,
    val character: String,
    val name: String,
    val userEventList: List<MyPageEntity>
)
