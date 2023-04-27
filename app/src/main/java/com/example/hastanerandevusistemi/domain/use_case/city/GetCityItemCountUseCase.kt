package com.example.hastanerandevusistemi.domain.use_case.city

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.repository.CityRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCityItemCountUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke() = flow {
        emit(RequestState.Loading<Int>())
        val result = cityRepository.getCount()
        if (result == 0) {
            emit(RequestState.Error<Int>("No data found."))
            return@flow
        } else {
            emit(RequestState.Success<Int>(result))
        }
    }.catch {
        emit(
            RequestState.Error<Int>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}