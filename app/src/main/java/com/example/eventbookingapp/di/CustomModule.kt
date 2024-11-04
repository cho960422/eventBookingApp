package com.example.eventbookingapp.di

import com.example.eventbookingapp.di.impl.LocationModuleImpl
import com.example.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CustomModule {
    @Provides
    @Singleton
    fun provideLocationModule(locationRepository: LocationRepository): LocationModuleInterface = LocationModuleImpl(locationRepository)
}