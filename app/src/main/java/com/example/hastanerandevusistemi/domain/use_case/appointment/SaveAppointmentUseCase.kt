package com.example.hastanerandevusistemi.domain.use_case.appointment

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity
import com.example.hastanerandevusistemi.domain.model.Appointment
import com.example.hastanerandevusistemi.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveAppointmentUseCase @Inject constructor(var appointmentRepository: AppointmentRepository) {
    operator fun invoke(appointmentEntity: List<Appointment>) = flow {
        emit(RequestState.Loading<Long>())
        val result =
            appointmentRepository.insertAppointment(appointmentEntity.map { it.toAppointmentEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}