package com.example.domain.use_case.post_user

import com.example.domain.entities.state.Response
import com.example.domain.entities.user.User
import com.example.domain.repository.UserRepository
import com.example.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostUserUseCase @Inject constructor(private val repository: UserRepository) :
    BaseUseCase<User>() {
    operator fun invoke(request: User): Flow<Response<Boolean>> = flow {

        emit(
            Response.Success(
                repository.signUpUser(
                    request = request
                )
            )
        )
    }
}