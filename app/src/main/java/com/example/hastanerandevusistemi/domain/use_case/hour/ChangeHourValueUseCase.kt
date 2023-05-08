package com.example.hastanerandevusistemi.domain.use_case.hour

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.Appointment
import com.example.hastanerandevusistemi.domain.repository.HourRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangeHourValueUseCase @Inject constructor(private val hourRepository: HourRepository) {
    operator fun invoke(randevu : Appointment) = flow {
        emit(RequestState.Loading<Any>())
        val result = hourRepository.updateHour(randevu.dayId!!, randevu.hourId!!)
        emit(RequestState.Success<Any>(result))
    }.catch {
        emit(
            RequestState.Error<Any>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}