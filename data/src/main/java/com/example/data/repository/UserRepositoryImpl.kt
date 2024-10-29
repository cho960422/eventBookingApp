package com.example.data.repository

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    //private val api: LEMSApi
) : UserRepository {
    override suspend fun postUser(request: User): User {
        return request
    }
}