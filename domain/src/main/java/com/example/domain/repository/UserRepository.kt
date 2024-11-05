package com.example.domain.repository

import com.example.domain.entities.mypage.MyPageEntity
import com.example.domain.entities.event.EventUserListEntity
import com.example.domain.entities.user.PatchUserRequestEntity
import com.example.domain.entities.user.User

interface UserRepository {
    suspend fun signUpUser(request: User): Boolean
    suspend fun getUser(): User
    suspend fun getMyPageInformation(): MyPageEntity
    suspend fun patchUser(request: PatchUserRequestEntity): Boolean
    suspend fun getEventJoinUsers(eventId: Long): List<EventUserListEntity>
}