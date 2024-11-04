package com.example.data.model.remote

import com.example.data.model.dto.base.BaseResponse
import com.example.data.model.dto.mypage.MyPageDto
import com.example.data.model.dto.user.EditProfileRequestDto
import com.example.data.model.dto.user.EventUserListDto
import com.example.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("/users/register")
    suspend fun signUp(
        @Body request: User
    ): Response<BaseResponse<User>>

    @GET("/users")
    suspend fun getUserInformation(): Response<BaseResponse<MyPageDto>>

    @PATCH("/users")
    suspend fun patchUserInformation(
        @Body requestBody: EditProfileRequestDto
    ): Response<BaseResponse<Any>>

    @GET("/users/{eventId}")
    suspend fun getUsersInEvent(
        @Path("eventId") eventId: Int
    ): Response<BaseResponse<List<EventUserListDto>>>
}