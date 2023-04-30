package com.example.hastanerandevusistemi.domain.use_case.user

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.UserEntity
import com.example.hastanerandevusistemi.domain.repository.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByTcAndPasswordUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(tc: Int, password: String) = flow {
        emit(RequestState.Loading<UserEntity>())
        val result = userRepository.getUserByTCAndPassword(tc, password)
        if (result != null) {
            emit(RequestState.Success(result))
        } else {
            emit(RequestState.Error<UserEntity>("Kullanıcı bulunamadı"))
        }
    }.catch {
        emit(
            RequestState.Error<UserEntity>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}