package com.example.eventbookingapp.repository.repository_interface

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.eventbookingapp.data.dto.event.EventDetailDto
import com.example.eventbookingapp.data.dto.event.EventListRoomEntity
import com.example.eventbookingapp.data.dto.event.EventWriteDto
import com.example.eventbookingapp.domain.entities.event.EventDetailEntity
import com.example.eventbookingapp.domain.entities.event.EventWriteRequestEntity
import com.example.eventbookingapp.domain.entities.event.SearchOptions
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun writeEvent(dto: EventWriteDto): Boolean
    suspend fun getEvent(id: String): EventDetailDto
    suspend fun deleteEvent(id: String): Boolean
    suspend fun patchEvent(dto: EventWriteDto): Boolean
    suspend fun editCategoryOptions(category: Int)
    suspend fun editSortOptions(sortBy: Int)
    fun getSearchOptions(): Flow<SearchOptions>
    fun getEventList(query: String): Flow<PagingData<EventListRoomEntity>>
}