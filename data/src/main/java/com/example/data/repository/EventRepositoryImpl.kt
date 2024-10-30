package com.example.data.repository

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.ApplicationContextProvider
import com.example.data.core.categoryKey
import com.example.data.core.searchOptionsDataStore
import com.example.data.core.sortByKey
import com.example.data.paging_source.HomeEventPagingSource
import com.example.data.remote_mediator.HomeEventRemoteMediator
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
    private val service: com.example.data.remote.EventService,
    private val db: com.example.data.AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val applicationContextProvider: ApplicationContextProvider
): EventRepository {
    private val context = applicationContextProvider.getContext()
    private val pref = context.searchOptionsDataStore

    override suspend fun writeEvent(dto: com.example.data.dto.event.EventWriteDto): Boolean {
        return withContext(ioDispatcher) {
            val response = service.submitEvent(dto)

            response.isSuccessful
        }
    }

    override suspend fun getEvent(id: String): com.example.data.dto.event.EventDetailDto {
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

    override suspend fun patchEvent(dto: com.example.data.dto.event.EventWriteDto): Boolean {
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
    override fun getSearchOptions(): Flow<com.example.domain.entities.event.SearchOptions> {
        return pref.data.flatMapLatest {
            val category = it[intPreferencesKey(categoryKey)]
            val sortBy = it[intPreferencesKey(sortByKey)]
            flow {
                val obj = com.example.domain.entities.event.SearchOptions(
                    if (category == -1) null else category, if (sortBy == -1) null else sortBy
                )

                emit(obj)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getEventList(query:String): Flow<PagingData<com.example.data.dto.event.EventListRoomEntity>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = HomeEventRemoteMediator(service, db, query)
        ) {
            HomeEventPagingSource(db, query)
        }

        return pager.flow
    }
}