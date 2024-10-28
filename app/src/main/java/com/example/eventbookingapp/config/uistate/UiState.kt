package com.example.eventbookingapp.config.uistate

data class UiState(
    val query: String = "",
    val lastQueryScrolled: String = "",
    val hasNotScrolledForCurrentSearch: Boolean = false
)