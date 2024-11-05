package com.example.data

import com.example.data.core.AuthInterceptor
import com.example.data.model.dto.user.EventUserListDto
import com.example.data.model.remote.UserService
import com.example.domain.model.User
import com.example.domain.repository.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserTest {
    private lateinit var service: UserService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        runBlocking {
            server = MockWebServer()
            val tokenRepository: TokenRepository = mock()
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(tokenRepository))
                .build()

            whenever(tokenRepository.getToken()).thenReturn("1111111")

            service = Retrofit.Builder()
                .baseUrl(server.url(""))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
    }

    @After
    fun shutDown() {
        server.shutdown()
    }

    @Test
    fun `모임 회원 조회 테스트`() {
        runBlocking {
            val eventId = 1
            val successResponse = enqueueMockResponse(javaClass.classLoader!!, "event_user_list_success.json")
            val answer = listOf(EventUserListDto(name = "이길동", gender = "M", introduction = "성남시 분당구에 사는 직장인입니다."))

            server.enqueue(successResponse)
            val response = service.getUsersInEvent(eventId)

            Assert.assertTrue(server.takeRequest().getHeader("Authorization") == "1111111")

            Assert.assertArrayEquals((response.body()?.data?: listOf()).toTypedArray(), answer.toTypedArray())
        }
    }

    @Test
    fun `회원 가입 성공 테스트`() {
        runBlocking {
            val user = User(
                email = "cho960422@gmail.com",
                username = "조현국",
                password = "1111"
            )
            val successResponse = enqueueMockResponse(javaClass.classLoader!!, "check_member_information_success.json")
            server.enqueue(successResponse)

            val response = service.signUp(user)

            Assert.assertTrue(response.isSuccessful)
        }
    }
}