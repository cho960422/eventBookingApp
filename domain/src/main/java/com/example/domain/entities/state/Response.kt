package com.example.domain.entities.state

sealed class Response<T>(val data: T? = null, val error: com.example.domain.entities.state.IssueError? = null) {
    class Success<T>(data: T?) :
        Response<T>(data = data)

    class Error<T>(data: T? = null, issueError: com.example.domain.entities.state.IssueError) :
        Response<T>(data = data, error = issueError)

    class Loading<T> : Response<T>()
}