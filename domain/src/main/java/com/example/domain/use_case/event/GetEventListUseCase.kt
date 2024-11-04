package com.example.domain.use_case.event

import com.example.domain.entities.event.EventListEntity
import com.example.domain.entities.paging.PagingResult
import com.example.domain.entities.state.EXCEPTION
import com.example.domain.entities.state.IssueError
import com.example.domain.entities.state.Response
import com.example.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventListUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    operator fun invoke(query: String): Flow<Response<List<EventListEntity>>> = flow {
        emit(Response.Loading())

        try {
            val result = eventRepository.getEventList(query)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(
                Response.Error(issueError = IssueError(exception = EXCEPTION.UNKNOWN))
            )
        }
    }
}