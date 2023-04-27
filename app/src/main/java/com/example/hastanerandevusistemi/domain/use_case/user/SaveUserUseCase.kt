package com.example.hastanerandevusistemi.domain.use_case.user

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.User
import com.example.hastanerandevusistemi.domain.repository.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(var repository: UserRepository) {
    operator fun invoke(userModel: User) = flow {
        emit(RequestState.Loading<Long>())
        val result = repository.insertUser(userModel.toUserEntity())
        emit(RequestState.Success<Long>(result))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}