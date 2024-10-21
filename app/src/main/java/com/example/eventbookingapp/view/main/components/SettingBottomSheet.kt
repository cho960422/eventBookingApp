package com.example.eventbookingapp.view.main.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

/**
 * 설정 전용 바텀시트 다이얼로그
 * 바텀시트의 상태값은 지역적으로 관리
 * 바텀시트의 동작 이후 전달되는 콜백값으로 보이지 않음이 확인되었을 때 상위 레벨에서 다른 작업을 할 수 있도록 onDismiss 콜백 함수 호출
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingBottomSheet(
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismiss,
    ) {
        Button(onClick = {
            scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                if (!modalBottomSheetState.isVisible) {
                    onDismiss()
                }
            }
        }) {
            Text(text = "테스트 버튼 ")
        }
    }
}