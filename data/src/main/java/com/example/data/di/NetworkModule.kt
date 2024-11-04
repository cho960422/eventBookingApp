package com.example.data.di

import androidx.room.Room
import com.example.data.core.ApplicationContextProvider
import com.example.data.core.AuthInterceptor
import com.example.data.core.LoggingInterceptor
import com.example.data.core.cacheSize
import com.example.data.model.AppDatabase
import com.example.data.model.remote.EventService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenRepository: com.example.domain.repository.TokenRepository): AuthInterceptor {
        return AuthInterceptor(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor, applicationContextProvider: ApplicationContextProvider): OkHttpClient {
        /**
        Set File application cache file directory
        size : 3MB
         */
        val cache = Cache(
            applicationContextProvider.getContext().cacheDir,
            cacheSize
        )

        return OkHttpClient()
            .newBuilder()
            .cache(cache)
            .addInterceptor(LoggingInterceptor())
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit2Client(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://test.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEventService(retrofit: Retrofit): EventService = retrofit.create(
        EventService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(applicationContextProvider: ApplicationContextProvider): AppDatabase =
        Room.databaseBuilder(
            applicationContextProvider.getContext(),
            AppDatabase::class.java, "lems-database"
        )
            .build()
}