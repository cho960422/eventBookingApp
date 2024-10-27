package com.example.eventbookingapp.domain.use_case

import com.example.eventbookingapp.EXCEPTION
import com.example.eventbookingapp.IssueError
import com.example.eventbookingapp.common.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

abstract class BaseUseCase<T> {
    fun execute(
        apiCall: suspend () -> T,
    ): Flow<Response<T>> = flow {
        try {
            emit(Response.Loading())
            val response = apiCall()
            emit(Response.Success(data = response))
        } catch (e: retrofit2.HttpException) {
            emit(
                Response.Error(
                    issueError = IssueError().getError(e.code())
                )
            )
        } catch (e: IOException) {
            emit(Response.Error(issueError = IssueError(exception = EXCEPTION.IOEXCEPTION)))
        }
    }
}