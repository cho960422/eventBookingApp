package com.example.eventbookingapp.presentation.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginIcon(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { /*TODO*/ }
    ) {
        Icon(imageVector = Icons.Filled.Lock, contentDescription = "로그인 버튼")
    }
}