package com.example.data.model.remote

import com.example.data.model.dto.base.BaseResponse
import com.example.data.model.dto.mypage.MyPageDto
import com.example.data.model.dto.user.PatchProfileRequestDto
import com.example.data.model.dto.user.EventUserListDto
import com.example.domain.entities.user.User
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
    ): Response<BaseResponse<Nothing>>

    @GET("/users")
    suspend fun getUserInformation(): Response<BaseResponse<MyPageDto>>

    @PATCH("/users")
    suspend fun patchUserInformation(
        @Body requestBody: PatchProfileRequestDto
    ): Response<BaseResponse<Nothing>>

    @GET("/users/{eventId}")
    suspend fun getUsersInEvent(
        @Path("eventId") eventId: Int
    ): Response<BaseResponse<List<EventUserListDto>>>
}