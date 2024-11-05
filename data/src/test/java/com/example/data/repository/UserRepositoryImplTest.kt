package com.example.data.repository

import com.example.data.model.dto.base.BaseResponse
import com.example.data.model.dto.mypage.MyPageDto
import com.example.data.model.dto.mypage.UserEventDto
import com.example.data.model.dto.user.EventUserListDto
import com.example.data.model.dto.user.PatchProfileRequestDto
import com.example.data.model.remote.UserService
import com.example.domain.entities.user.PatchUserRequestEntity
import com.example.domain.entities.user.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class UserRepositoryImplTest {
    private lateinit var repository: UserRepository

    @BeforeEach
    fun mockSetting() {
        val mockService = mock<UserService>()
        repository = UserRepositoryImpl(mockService)
    }

    @Test
    fun signUpUser() {

    }

    @Test
    fun getUser() = runTest {
        val userInResponse = repository.getUser()

        assertTrue(userInResponse.username == )
    }

    @Test
    fun patchUser() = runTest {
        assertTrue(
            repository.patchUser(
                PatchUserRequestEntity(
                    username = "외양간",
                    introduction = "성남시 분당구에 사는 직장인입니다.",
                    localName = "서울시",
                    birth = "1999-05-22",
                    character = "ISTP"
                )
            )
        )
    }

    @Test
    fun getEventJoinUsers() = runTest {
        assertTrue(repository.getEventJoinUsers(1).size == 1)
        assertTrue(repository.getEventJoinUsers(2).isEmpty())
    }

    private fun mockingSignUpResponse(
        userService: UserService, user: User
    ) = runBlocking {
        val successResponse = Response.success(
            BaseResponse(
                data = null,
                message = "수정, 조회, 삭제, 등록 성공!"
            )
        )
        whenever(userService.signUp(user)).thenReturn(successResponse)
    }

    private fun mockingPatchUserInformationResponse(
        userService: UserService, request: PatchProfileRequestDto
    ) = runBlocking {
        val successResponse = Response.success(
            BaseResponse(
                data = null,
                message = "수정, 조회, 삭제, 등록 성공!"
            )
        )

        whenever(userService.patchUserInformation(request)).thenReturn(successResponse)
    }

    private fun mockingGetUsersInEvent(
        userService: UserService,
        eventId: Int
    ) = runBlocking {
        val successResponse = Response.success(
            BaseResponse(
                data = listOf(
                    EventUserListDto(
                        name = "이길동",
                        gender = "M",
                        introduction = "성남시 분당구에 사는 직장인입니다."
                    )
                ),
                message = "수정, 조회, 삭제, 등록 성공!"
            )
        )
        val successEmptyResponse = Response.success(
            BaseResponse(
                data = listOf<EventUserListDto>(),
                message = "수정, 조회, 삭제, 등록 성공!"
            )
        )

        if (eventId == 1) {
            whenever(userService.getUsersInEvent(eventId)).thenReturn(successResponse)
        } else {
            whenever(userService.getUsersInEvent(eventId)).thenReturn(successEmptyResponse)
        }
    }

    private fun mockingGetUserInformationResponse(
        userService: UserService, user: User
    ) = runBlocking {
        val successResponse = Response.success(
            BaseResponse(
                data = MyPageDto(
                    name = "하상록",
                    birth = "1999-05-22",
                    character = "ISTP",
                    userEventList = listOf(
                        UserEventDto(
                            bookmarkId = 1,
                            author = "김철수",
                            eventName = "한강 러닝 모임",
                            eventDate = "2024-11-05T06:53:48.905Z",
                            capacity = 100,
                            applicants = 50
                        )
                    ),
                    bookmarkList = listOf(
                        UserEventDto(
                            bookmarkId = 1,
                            author = "김철수",
                            eventName = "한강 러닝 모임",
                            eventDate = "2024-11-05T06:53:48.905Z",
                            capacity = 100,
                            applicants = 50
                        )
                    )
                ),
                message = "수정, 조회, 삭제, 등록 성공!"
            )
        )
        whenever(userService.getUserInformation()).thenReturn(successResponse)
    }
}