package com.example.data

import com.example.data.model.remote.UserService
import com.example.domain.model.User
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpTest {
    private lateinit var service: UserService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    @After
    fun shutDown() {
        server.shutdown()
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