package com.example.data.remote

import com.example.eventbookingapp.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface LEMSApi {

    @POST("/users/register")
    suspend fun postUser(
        @Body request: User
    ): User
}