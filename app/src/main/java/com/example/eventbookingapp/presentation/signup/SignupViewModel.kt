package com.example.eventbookingapp.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.eventbookingapp.common.Response
import com.example.eventbookingapp.domain.model.User
import com.example.eventbookingapp.domain.use_case.post_user.PostUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val postUserUseCase: PostUserUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(SignupState())
    val state: State<SignupState> = _state

    private fun postUser(user: User) {
        postUserUseCase(user).onEach { result ->
            when(result) {
                is Response.Success -> {
                    _state.value = SignupState(isSuccess = true, user = result.data)

                }
                is Response.Error -> {
                    _state.value = SignupState(isError = true)

                }
                is Response.Loading -> {
                    _state.value = SignupState(isLoading = true)
                }
            }
        }
    }
}