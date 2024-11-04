package com.example.domain.repository

import com.example.domain.entities.event.EventDetailEntity
import com.example.domain.entities.event.EventListEntity
import com.example.domain.entities.event.EventWriteRequestEntity
import com.example.domain.entities.event.SearchOptions
import com.example.domain.entities.paging.PagingResult
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun writeEvent(request: EventWriteRequestEntity): Boolean
    suspend fun getEvent(id: String): EventDetailEntity
    suspend fun deleteEvent(id: String): Boolean
    suspend fun patchEvent(request: EventWriteRequestEntity): Boolean
    suspend fun editCategoryOptions(category: Int)
    suspend fun editSortOptions(sortBy: Int)
    fun getSearchOptions(): Flow<SearchOptions>
    fun getEventList(query: String): List<EventListEntity>
}