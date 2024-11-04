package com.example.data.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.data.core.ApplicationContextProvider
import com.example.data.core.tokenKey
import com.example.data.core.userDataStore
import com.example.domain.repository.TokenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class TokenRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val applicationContextProvider: ApplicationContextProvider
): TokenRepository {
    override suspend fun getToken(): String {
        val context = applicationContextProvider.getContext()
        return withContext(ioDispatcher) {
            val pref = context.userDataStore.data.first()
            pref[stringPreferencesKey(tokenKey)] ?: ""
        }
    }

    override fun writeToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun wipeToken() {
        TODO("Not yet implemented")
    }
}