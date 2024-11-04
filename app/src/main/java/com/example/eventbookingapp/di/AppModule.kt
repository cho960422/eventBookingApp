package com.example.eventbookingapp.di

import com.example.data.core.ApplicationContextProvider
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.eventbookingapp.di.impl.ApplicationContextProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideApplicationContextProvider(): ApplicationContextProvider = ApplicationContextProviderImpl()
}