package com.example.presentation.signup

data class SignupState(
    val isLoading: Boolean = false,
//    val user: User? = null,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
//    val issueError: IssueError? = null
)