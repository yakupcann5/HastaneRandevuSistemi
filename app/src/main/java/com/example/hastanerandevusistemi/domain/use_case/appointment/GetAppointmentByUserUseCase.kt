package com.example.hastanerandevusistemi.domain.use_case.appointment

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity
import com.example.hastanerandevusistemi.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAppointmentByUserUseCase @Inject constructor(var appointmentRepository: AppointmentRepository) {
    operator fun invoke(userId: Int) = flow {
        emit(RequestState.Loading<List<AppointmentEntity>>())
        val result = appointmentRepository.getAllAppointment(userId)
        emit(RequestState.Success<List<AppointmentEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<AppointmentEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}
