package com.example.eventbookingapp.domain.use_case.post_user

import com.example.eventbookingapp.common.Response
import com.example.eventbookingapp.domain.model.User
import com.example.eventbookingapp.domain.repository.UserRepository
import com.example.eventbookingapp.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostUserUseCase @Inject constructor(private val repository: UserRepository) :
    BaseUseCase<User>() {
    operator fun invoke(request: User): Flow<Response<User>> = execute {
        repository.postUser(
            request = request
        )
    }
}