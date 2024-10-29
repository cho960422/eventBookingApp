package com.example.eventbookingapp.di

import androidx.room.Room
import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.cacheSize
import com.example.eventbookingapp.config.interceptors.AuthInterceptor
import com.example.eventbookingapp.config.interceptors.LoggingInterceptor
import com.example.data.AppDatabase
import com.example.data.remote.EventService
import com.example.domain.repository.TokenRepository
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
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        /**
        Set File application cache file directory
        size : 3MB
         */
        val cache = Cache(
            EventBookingApplication.applicationContext().cacheDir,
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
    fun provideEventService(retrofit: Retrofit): com.example.data.remote.EventService = retrofit.create(
        com.example.data.remote.EventService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(): com.example.data.AppDatabase =
        Room.databaseBuilder(
            EventBookingApplication.applicationContext(),
            com.example.data.AppDatabase::class.java, "lems-database"
        )
            .build()
}