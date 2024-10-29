package com.example.eventbookingapp.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventbookingapp.presentation.login.components.ApiLoginButton

@Composable
fun LoginUI(navController: NavController) {
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "외양간",
            modifier = Modifier.size(width = 199.dp, height = 40.dp),
            style = MaterialTheme.typography.titleLarge,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))

        TextField(
            value = idText,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { idText = it },
            label = { Text(text = "아이디") },
            placeholder = { Text("아이디를 입력하세요") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = pwText,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { pwText = it },
            label = { Text(text = "비밀번호") },
            placeholder = { Text(text = "비밀번호를 입력하세요") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            enabled = false
        ) {
            Text(text = "로그인")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "회원가입", Modifier.clickable(onClick = {navController.navigate("signup")}))
        Spacer(modifier = Modifier.height(56.dp))

        Text(text = "SNS 로그인")
        Spacer(modifier = Modifier.height(16.dp))

        ApiLoginButton(text = "카카오로 로그인", onClick = ::kakaoLogin)
        Spacer(modifier = Modifier.height(16.dp))

        ApiLoginButton(text = "Naver로 로그인", onClick = ::naverLogin)
        Spacer(modifier = Modifier.height(16.dp))

        ApiLoginButton(text = "Google로 로그인", onClick = ::googleLogin)
    }
}

// 각각의 로그인 버튼 클릭 리스너 구현
private fun kakaoLogin() {
    // Todo : 카카오 로그인 구현
}

private fun naverLogin() {
    // Todo : 네이버 로그인 구현
}

private fun googleLogin() {
    // Todo : 구글 로그인 구현
}
