package com.example.data.repository

import android.location.Location
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.data.core.ApplicationContextProvider
import com.example.data.core.locationDataStore
import com.example.data.core.locationKey
import com.example.domain.entities.location.CurrentLocationEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LocationRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val applicationContextProvider: ApplicationContextProvider
): com.example.domain.repository.LocationRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCurrentLocation(): Flow<CurrentLocationEntity?> {
        val context = applicationContextProvider.getContext()

        return context.locationDataStore.data.flatMapLatest { pref ->
            flow {
                val data = pref[stringPreferencesKey(locationKey)]
                val location: CurrentLocationEntity? = if (data == null) {
                    null
                } else {
                    val list = data.split(",")
                    val convert = CurrentLocationEntity(
                        latitude = list[0].toDouble(),
                        longitude = list[1].toDouble()
                    )

                    convert
                }

                emit(location)
            }
        }
    }

    override fun updateCurrentLocation(location: CurrentLocationEntity) {
        val context = applicationContextProvider.getContext()

        CoroutineScope(ioDispatcher).launch {
            context.locationDataStore.edit {
                it[stringPreferencesKey(locationKey)] = locationToString(location)
            }
        }
    }

    private fun locationToString(location: CurrentLocationEntity): String {
        return "${location.latitude},${location.longitude}"
    }
}