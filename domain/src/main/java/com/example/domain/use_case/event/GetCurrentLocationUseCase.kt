package com.example.domain.use_case.event

import com.example.domain.entities.location.CurrentLocationEntity
import com.example.domain.entities.location.DefaultLocation
import com.example.domain.entities.state.Response
import com.example.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(): Flow<Response<CurrentLocationEntity>> = flow {
        emit(Response.Loading())

        val currentLocation = locationRepository.getCurrentLocation()
    }
}