package com.example.data.repository

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.data.core.ApplicationContextProvider
import com.example.data.core.categoryKey
import com.example.data.core.searchOptionsDataStore
import com.example.data.core.sortByKey
import com.example.data.model.AppDatabase
import com.example.data.model.remote.EventService
import com.example.domain.entities.event.EventDetailEntity
import com.example.domain.entities.event.EventListEntity
import com.example.domain.entities.event.EventWriteRequestEntity
import com.example.domain.entities.event.SearchOptions
import com.example.domain.repository.EventRepository
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
    private val db: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val applicationContextProvider: ApplicationContextProvider
): EventRepository {
    private val context = applicationContextProvider.getContext()
    private val pref = context.searchOptionsDataStore

    override suspend fun writeEvent(request: EventWriteRequestEntity): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getEvent(id: String): EventDetailEntity {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(id: String): Boolean {
        return withContext(ioDispatcher) {
            val response = service.deleteEvent(id.toInt())

            response.isSuccessful
        }
    }

    override suspend fun patchEvent(request: EventWriteRequestEntity): Boolean {
        TODO("Not yet implemented")
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
    override fun getSearchOptions(): Flow<com.example.domain.entities.event.SearchOptions> {
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

    override fun getEventList(query:String): List<EventListEntity> {
        return listOf()
    }
}