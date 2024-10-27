package com.example.eventbookingapp.view.main

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigation.findNavController
import com.example.eventbookingapp.R
import com.example.eventbookingapp.view.main.viewmodel.HomeScreenViewModel
import com.google.android.gms.location.FusedLocationProviderClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    client: FusedLocationProviderClient,
    locationPermissionLauncher: ActivityResultLauncher<Array<String>>,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp),
                title = {Text(text = "123")},
                actions = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.baseline_account_circle_24
                        ),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable {
                            },
                        contentDescription = "생성 버튼",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
            )
        }
    ) { padding ->
        val innerPadding = padding
        val context: Context = LocalContext.current
        val activity = context as? ComponentActivity

        Column(modifier = Modifier.padding(innerPadding)) {
            Button(
                onClick = {
                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    )
//                    viewModel.onUpdateLocationRequest(client)
                }
            ) {
                Text(text = "위치 권한 요청")
            }
        }
    }
}