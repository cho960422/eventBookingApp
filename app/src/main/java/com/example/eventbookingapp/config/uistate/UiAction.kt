package com.example.eventbookingapp.config.uistate

sealed class UiAction {
    data class Search(
        val query: String
    ): UiAction()

    data class Scroll(
        val currentQuery: String,
    )
}