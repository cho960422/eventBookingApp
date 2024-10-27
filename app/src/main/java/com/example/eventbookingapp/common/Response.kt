package com.example.eventbookingapp.common

import com.example.eventbookingapp.IssueError

sealed class Response<T>(val data: T? = null, val error: IssueError? = null) {
    class Success<T>(data: T?) :
        Response<T>(data = data)

    class Error<T>(data: T? = null, issueError: IssueError) :
        Response<T>(data = data, error = issueError)

    class Loading<T> : Response<T>()
}