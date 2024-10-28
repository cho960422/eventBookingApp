package com.example.eventbookingapp.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventbookingapp.R

@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onFilterTriggered: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = Color.White, RoundedCornerShape(50.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = query,
            onValueChange = onQueryChanged,
            placeholder = {
                Text(
                    text = "검색어를 입력해주세요.",
                    style = LocalTextStyle.current
                )
            },
            leadingIcon = {
                IconButton(
                    onClick = onFilterTriggered
                ) {
                    Icon(painter = painterResource(id = R.drawable.filter_variant), contentDescription = "옵션 버튼")
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = onSearchTriggered
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Search,
                        contentDescription = ""
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchTriggered()
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Preview
@Composable
fun HomeSearchBarPreview() {
    HomeSearchBar(query = "", onFilterTriggered = {}, onQueryChanged = {

    }) {

    }
}