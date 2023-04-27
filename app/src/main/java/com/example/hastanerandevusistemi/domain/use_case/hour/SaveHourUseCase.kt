package com.example.hastanerandevusistemi.domain.use_case.hour

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.Saatler
import com.example.hastanerandevusistemi.domain.repository.HourRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveHourUseCase @Inject constructor(private val hourRepository: HourRepository) {
    operator fun invoke(hour: List<Saatler>) = flow {
        emit(RequestState.Loading<Long>())
        val result = hourRepository.insertHour(hour.map { it.toHourEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}