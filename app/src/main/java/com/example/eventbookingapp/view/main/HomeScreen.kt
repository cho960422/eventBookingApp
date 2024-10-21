package com.example.eventbookingapp.view.main

import android.Manifest
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventbookingapp.config.defaultPadding
import com.example.eventbookingapp.ui.theme.BackgroundGray
import com.example.eventbookingapp.view.main.components.HomeSearchBar
import com.example.eventbookingapp.view.main.components.LoginIcon
import com.example.eventbookingapp.view.main.components.SettingBottomSheet
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
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundGray
    ) { padding ->
        val innerPadding = padding
        val context: Context = LocalContext.current
        val activity = context as? ComponentActivity
        val currentLocation = viewModel.currentLocation.collectAsState()
        var showSettingSheet by remember {
            mutableStateOf(false)
        }

        // 바텀시트가 열려지는 상태라면 바텀시트 생성
        if (showSettingSheet) {
            SettingBottomSheet {
                // onDismiss 함수 구현
                // SettingBottomSheet 메모리 해제를 위해 상태값 변경
                showSettingSheet = false
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeSearchBar(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            vertical = defaultPadding
                        )
                        .padding(start = defaultPadding)
                    ,
                    query = "", // 현재 검색창 입력값
                    onFilterTriggered = {
                        showSettingSheet = true
                    }, // 필터 버튼 클릭 시 콜백 함수
                    onQueryChanged = {}, // 글자 변경 시 상태값 변경 콜백
                    onSearchTriggered = {} // 검색 액션 시 콜백
                ) // 검색 바
                LoginIcon(
                    modifier = Modifier.padding(defaultPadding / 2)
                ) {

                } // 로그인 또는 사용자 정보 아이콘
            } // 상단 Top Bar

            LazyColumn {

            }
        }
    }
}