package com.example.domain.repository

import com.example.domain.model.EventUserListEntity
import com.example.domain.model.PatchUserRequestEntity
import com.example.domain.model.User

interface UserRepository {
    suspend fun signUpUser(request: User): Boolean
    suspend fun getUser(): User
    suspend fun patchUser(request: PatchUserRequestEntity): Boolean
    suspend fun getEventJoinUsers(eventId: Long): List<EventUserListEntity>
}