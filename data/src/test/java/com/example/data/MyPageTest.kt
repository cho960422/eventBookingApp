package com.example.data

import com.example.data.core.AuthInterceptor
import com.example.data.model.remote.UserService
import com.example.data.repository.TokenRepositoryImpl
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

class MyPageTest {
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
    fun `마이페이지 호출 테스트`() {
        runBlocking {
            val response = enqueueMockResponse(javaClass.classLoader!!, "check_member_information_success.json")

            server.enqueue(response)
            val userInformation = service.getUserInformation().body()?.data

            Assert.assertTrue(userInformation?.name == "하상록")
            Assert.assertTrue(userInformation?.birth == "1999-05-22")
            Assert.assertTrue(userInformation?.character == "ISTP")
            Assert.assertTrue(userInformation?.userEventList?.get(0)?.bookmarkId == 1)
            Assert.assertTrue(userInformation?.userEventList?.get(0)?.author == "김철수")
            Assert.assertTrue(userInformation?.userEventList?.get(0)?.eventName == "한강 러닝 모임")
            Assert.assertTrue(userInformation?.userEventList?.get(0)?.eventDate == "2024-11-04T08:42:52.506Z")
            Assert.assertTrue(userInformation?.userEventList?.get(0)?.capacity == 100)
            Assert.assertTrue(userInformation?.userEventList?.get(0)?.applicants == 50)
            Assert.assertTrue(userInformation?.bookmarkList?.get(0)?.bookmarkId == 1)
            Assert.assertTrue(userInformation?.bookmarkList?.get(0)?.author == "김철수")
            Assert.assertTrue(userInformation?.bookmarkList?.get(0)?.eventName == "한강 러닝 모임")
            Assert.assertTrue(userInformation?.bookmarkList?.get(0)?.eventDate == "2024-11-04T08:42:52.506Z")
            Assert.assertTrue(userInformation?.bookmarkList?.get(0)?.capacity == 100)
            Assert.assertTrue(userInformation?.bookmarkList?.get(0)?.applicants == 50)
        }
    }
}