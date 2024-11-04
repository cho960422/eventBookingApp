package com.example.data.model.dto.mypage

data class MyPageDto(
    val birth: String,
    val bookmarkList: List<Bookmark>,
    val character: String,
    val name: String,
    val userEventList: List<UserEvent>
)