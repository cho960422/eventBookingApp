package com.example.eventbookingapp.presentation.signup

import com.example.eventbookingapp.IssueError
import com.example.eventbookingapp.domain.model.User

data class SignupState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val issueError: IssueError? = null
)