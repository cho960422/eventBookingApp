package com.example.eventbookingapp.repository.implementation

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.eventbookingapp.EventBookingApplication
import com.example.eventbookingapp.config.categoryKey
import com.example.eventbookingapp.config.locationDataStore
import com.example.eventbookingapp.config.searchOptionsDataStore
import com.example.eventbookingapp.config.sortByKey
import com.example.eventbookingapp.model.dto.event.EventDetailDto
import com.example.eventbookingapp.model.dto.event.EventWriteDto
import com.example.eventbookingapp.model.service.EventService
import com.example.eventbookingapp.repository.repository_interface.EventRepository
import com.example.eventbookingapp.view.entities.event.SearchOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val service: EventService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): EventRepository {
    private val context = EventBookingApplication.applicationContext()
    private val pref = context.searchOptionsDataStore

    override suspend fun writeEvent(dto: EventWriteDto): Boolean {
        return withContext(ioDispatcher) {
            val response = service.submitEvent(dto)

            response.isSuccessful
        }
    }

    override suspend fun getEvent(id: String): EventDetailDto {
        return withContext(ioDispatcher) {
            val response = service.getSingleEvent(id.toInt())

            if (response.isSuccessful && response.body()?.data != null) {
                response.body()!!.data!!
            } else {
                throw Exception()
            }
        }
    }

    override suspend fun deleteEvent(id: String): Boolean {
        return withContext(ioDispatcher) {
            val response = service.deleteEvent(id.toInt())

            response.isSuccessful
        }
    }

    override suspend fun patchEvent(dto: EventWriteDto): Boolean {
        return withContext(ioDispatcher) {
            val response = service.patchEvent(dto)

            response.isSuccessful
        }
    }

    override suspend fun editCategoryOptions(category: Int) {
        pref.edit {
            it[intPreferencesKey(categoryKey)] = category
        }
    }

    override suspend fun editSortOptions(sortBy: Int) {
        pref.edit {
            it[intPreferencesKey(sortByKey)] = sortBy
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSearchOptions(): Flow<SearchOptions> {
        return pref.data.flatMapLatest {
            val category = it[intPreferencesKey(categoryKey)]
            val sortBy = it[intPreferencesKey(sortByKey)]
            flow {
                val obj = SearchOptions(
                    if (category == -1) null else category, if (sortBy == -1) null else sortBy
                )

                emit(obj)
            }
        }
    }
}