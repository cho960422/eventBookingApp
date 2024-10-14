package com.example.eventbookingapp.model.service

import com.example.eventbookingapp.model.dto.base.BaseResponse
import com.example.eventbookingapp.model.dto.event.EventDetailDto
import com.example.eventbookingapp.model.dto.event.EventWriteDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface EventService {
    @POST("/events")
    suspend fun submitEvent(
        requestBody: EventWriteDto
    ): Response<BaseResponse<Any>>

    @GET("/events/{id}")
    suspend fun getSingleEvent(
        @Query("id") id: Int
    ): Response<BaseResponse<EventDetailDto>>

    @DELETE("/events/{id}")
    suspend fun deleteEvent(
        @Query("id") id: Int
    ): Response<BaseResponse<Any>>

    // TODO()
    // API 문서 미완성
    @PATCH("/events/{id}")
    suspend fun patchEvent(
        requestBody: EventWriteDto
    ): Response<BaseResponse<Any>>

//    @POST("/events")
//    suspend fun submitEvent(requestBody: EventWriteDto): Response<DefaultResponse>
}