package com.example.domain.entities.state

sealed class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val isError: Boolean = false,
    val errorCode: Int? = null,
    val errMsg: String? = null
)

class Loading<T> : DataState<T>(isLoading = true)
class Success<T>(data: T?) : DataState<T>(data = data)
class Error<T>(errorCode: Int? = null, errMsg: String? = null) :
    DataState<T>(isError = true, errorCode = errorCode, errMsg = errMsg)