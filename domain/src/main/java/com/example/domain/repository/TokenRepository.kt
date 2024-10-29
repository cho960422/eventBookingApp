package com.example.domain.repository

interface TokenRepository {
    suspend fun getToken(): String
    fun writeToken(token: String)
    fun wipeToken()
}