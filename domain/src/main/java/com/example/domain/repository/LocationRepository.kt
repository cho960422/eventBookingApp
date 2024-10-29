package com.example.domain.repository

import com.example.domain.entities.location.CurrentLocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getCurrentLocation(): Flow<CurrentLocationEntity?>
    fun updateCurrentLocation(location: CurrentLocationEntity)
}