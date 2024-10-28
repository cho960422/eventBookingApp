package com.example.eventbookingapp.domain.repository

import com.example.eventbookingapp.domain.model.User

interface UserRepository {
    suspend fun postUser(request: User): User
}