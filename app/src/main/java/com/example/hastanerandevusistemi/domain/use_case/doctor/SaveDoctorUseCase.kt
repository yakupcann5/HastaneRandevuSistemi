package com.example.hastanerandevusistemi.domain.use_case.doctor

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.Doktor
import com.example.hastanerandevusistemi.domain.repository.DoctorRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveDoctorUseCase @Inject constructor(private val doctorRepository: DoctorRepository) {
    operator fun invoke(doctors: List<Doktor>) = flow {
        emit(RequestState.Loading<Long>())
        val result = doctorRepository.insertDoctors(doctors.map { it.toDoctorEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}