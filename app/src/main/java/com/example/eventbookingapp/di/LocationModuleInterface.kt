package com.example.eventbookingapp.di

import android.location.Location
import com.example.domain.entities.location.CurrentLocationEntity
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.Flow

interface LocationModuleInterface {
    fun syncCurrentLocation(client: FusedLocationProviderClient)
    fun getCurrentLocation(): Flow<CurrentLocationEntity?>
    fun checkPermissionApprove(): String
}