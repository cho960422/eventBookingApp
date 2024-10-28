package com.example.eventbookingapp.data.repository

import com.example.eventbookingapp.data.remote.LEMSApi
import com.example.eventbookingapp.domain.model.User
import com.example.eventbookingapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    //private val api: LEMSApi
) : UserRepository {
    override suspend fun postUser(request: User): User {
        return request
    }
}