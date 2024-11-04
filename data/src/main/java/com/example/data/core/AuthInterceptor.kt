package com.example.data.core

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenRepository: com.example.domain.repository.TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = runBlocking {
            tokenRepository.getToken()
        }

        val request = chain.request().newBuilder()
            .addHeader(headerTokenKey, token)
            .build()

        return chain.proceed(request)
    }
}