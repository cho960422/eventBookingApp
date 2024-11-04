package com.example.eventbookingapp.di

import com.example.data.core.ApplicationContextProvider
import com.example.data.model.AppDatabase
import com.example.eventbookingapp.BuildConfig
import com.example.data.model.remote.EventService
import com.example.data.repository.EventRepositoryImpl
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
        eventService: EventService,
        db: AppDatabase,
        applicationContextProvider: ApplicationContextProvider
    ): com.example.domain.repository.EventRepository =
        EventRepositoryImpl(
            service = eventService,
            db = db,
            applicationContextProvider = applicationContextProvider
        )
}