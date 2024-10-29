package com.example.domain.use_case.event

import com.example.domain.entities.event.EventListEntity
import com.example.domain.entities.paging.PagingResult
import com.example.domain.entities.state.DataState
import com.example.domain.entities.state.Error
import com.example.domain.entities.state.Loading
import com.example.domain.entities.state.Success
import com.example.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventListUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    operator fun invoke(query: String): Flow<DataState<PagingResult<Int, EventListEntity>>> = flow {
        emit(Loading())

        try {
            val result = eventRepository.getEventList(query)
            emit(Success(result))
        } catch (e: Exception) {
            emit(Error())
        }
    }
}