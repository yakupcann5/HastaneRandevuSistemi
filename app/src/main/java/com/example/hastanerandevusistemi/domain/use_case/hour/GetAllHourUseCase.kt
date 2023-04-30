package com.example.hastanerandevusistemi.domain.use_case.hour

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.HourEntity
import com.example.hastanerandevusistemi.domain.repository.HourRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllHourUseCase @Inject constructor(var hourRepository: HourRepository) {
    operator fun invoke(dayId: Int) = flow {
        emit(RequestState.Loading<List<HourEntity>>())
        val result = hourRepository.getAllHour(dayId)
        emit(RequestState.Success<List<HourEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<HourEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}