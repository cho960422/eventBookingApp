package com.example.eventbookingapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        val layoutPadding = paddingValues

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Hello World")
        }
    }
}