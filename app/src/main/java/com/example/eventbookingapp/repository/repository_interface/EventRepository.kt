package com.example.eventbookingapp.repository.repository_interface

import com.example.eventbookingapp.model.dto.event.EventDetailDto
import com.example.eventbookingapp.model.dto.event.EventWriteDto
import com.example.eventbookingapp.view.entities.event.EventDetailEntity
import com.example.eventbookingapp.view.entities.event.EventWriteRequestEntity
import com.example.eventbookingapp.view.entities.event.SearchOptions
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun writeEvent(dto: EventWriteDto): Boolean
    suspend fun getEvent(id: String): EventDetailDto
    suspend fun deleteEvent(id: String): Boolean
    suspend fun patchEvent(dto: EventWriteDto): Boolean
    suspend fun editCategoryOptions(category: Int)
    suspend fun editSortOptions(sortBy: Int)
    fun getSearchOptions(): Flow<SearchOptions>
}