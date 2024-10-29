package com.example.eventbookingapp.di

import com.example.eventbookingapp.BuildConfig
import com.example.data.AppDatabase
import com.example.data.remote.EventService
import com.example.data.repository.EventRepositoryImpl
import com.example.data.repository.LocationRepositoryImpl
import com.example.data.repository.TokenRepositoryImpl
import com.example.domain.repository.EventRepository
import com.example.domain.repository.LocationRepository
import com.example.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTokenRepository(): com.example.domain.repository.TokenRepository {
        /**
         * 모듈 테스트 시, Dispatchers를 변경해서 넣을 수 있음
         * 지금은 아직 방법을 몰라서 같은 Dispatchers를 넣었습니다.
         */
        val dispatchers: CoroutineDispatcher = if (BuildConfig.DEBUG) {
            Dispatchers.IO
        } else {
            Dispatchers.IO
        }
        return com.example.data.repository.TokenRepositoryImpl(dispatchers)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(): com.example.domain.repository.LocationRepository =
        com.example.data.repository.LocationRepositoryImpl()

    @Provides
    @Singleton
    fun provideEventRepository(
        eventService: com.example.data.remote.EventService,
        db: com.example.data.AppDatabase
    ): com.example.domain.repository.EventRepository =
        com.example.data.repository.EventRepositoryImpl(service = eventService, db = db)
}