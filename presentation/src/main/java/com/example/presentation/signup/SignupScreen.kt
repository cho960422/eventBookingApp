package com.example.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventbookingapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Signup() {
    val context = LocalContext.current
    var nickname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var pw1 by rememberSaveable { mutableStateOf("") }
    var pw2 by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp),
                title = {
                    Text("가입하기", fontSize = 22.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // Todo : 뒤로 가기 기능 구현
                            Toast.makeText(context, "뒤로 가기", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기 버튼"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NicknameInputField(nickname) { newNickname -> nickname = newNickname}
            EmailInputField(email) { newEmail -> email = newEmail}
            PW1InputField(pw1) { newPw1 -> pw1 = newPw1}
            PW2InputField(pw1,pw2) { newPw2 -> pw2 = newPw2}
            SignUpButton(nickname, email, pw1, pw2)
        }
    }
}

@Composable
fun NicknameInputField(nickname: String, onNicknameChange: (String) -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    OutlinedTextField(
        value = nickname,
        modifier = Modifier
            .fillMaxWidth(),
        onValueChange = onNicknameChange,
        label = { Text(text = "닉네임") },
        placeholder = { Text(text = "닉네임을 입력하세요") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "NicknameIcon"
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}

@Composable
fun EmailInputField(email: String, onEmailChange: (String) -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    OutlinedTextField(
        value = email,
        modifier = Modifier
            .fillMaxWidth(),
        onValueChange = onEmailChange,
        label = { Text(text = "이메일 주소") },
        placeholder = { Text(text = "이메일을 입력하세요") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "EmailIcon"
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun PW1InputField(pw1: String, onPw1Change: (String) -> Unit) {
    var showPw1 by remember { mutableStateOf(value = false) }
    val pwPattern = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=]).{8,16}$")
    val pwCondition = pw1.isNotEmpty() && !pw1.matches(pwPattern)

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = pw1,
            onValueChange = onPw1Change,
            label = { Text(text = "비밀번호") },
            placeholder = { Text(text = "비밀번호를 입력하세요") },
            isError = pwCondition,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPw1) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "PW1Icon"
                )
            },
            trailingIcon = {
                if (showPw1) {
                    IconButton(onClick = { showPw1 = false }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPw1 = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
        )
    }
    if (pwCondition) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~16자리여야 합니다.",
            color = Color.Red
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun PW2InputField(pw1: String, pw2: String, onPw2Change: (String) -> Unit) {
    var showPw2 by remember { mutableStateOf(value = false) }
    val pwCondition = pw1 != pw2
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = pw2,
        onValueChange = onPw2Change,
        label = { Text(text = "비밀번호 재입력") },
        placeholder = { Text(text = "비밀번호를 입력하세요") },
        isError = pwCondition,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (showPw2) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "PWIcon"
            )
        },
        trailingIcon = {
            if (showPw2) {
                IconButton(onClick = { showPw2 = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_visibility_24),
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPw2 = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                        contentDescription = "hide_password"
                    )
                }
            }
        },
    )
    if (pwCondition) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "비밀번호가 일치하지 않아요",
            color = Color.Red
        )
    }
    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun SignUpButton(nickname: String, email: String, pw1: String, pw2: String) {
    val pwPattern = Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=]).{8,16}$")
    val pw1Condition = (pw1.isNotEmpty() && pw1.matches(pwPattern))
    val pw2Condition = (pw1 == pw2)
    val signUpCondition = nickname.isNotEmpty() && email.isNotEmpty() && pw1Condition && pw2Condition
    Button(modifier = Modifier.fillMaxWidth(),onClick = { /*TODO*/ }, enabled = signUpCondition) {
        Text(text = "회원가입")
    }
}
