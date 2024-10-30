package com.example.domain.use_case

import com.example.domain.entities.state.EXCEPTION
import com.example.domain.entities.state.IssueError
import com.example.domain.entities.state.Response
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
        }
        // Retrofit2에 연관된 Exception을 처리할 수 있는 객체를 생성해야 함
        catch (e: IOException) {
            emit(Response.Error(issueError = IssueError(exception = EXCEPTION.IOEXCEPTION)))
        }
    }
}