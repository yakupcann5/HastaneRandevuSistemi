package com.example.hastanerandevusistemi.domain.use_case.hospital

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.Hastane
import com.example.hastanerandevusistemi.domain.repository.HospitalRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveHospitalUseCase @Inject constructor(private val hospitalRepository: HospitalRepository) {
    operator fun invoke(hospital: List<Hastane>) = flow {
        emit(RequestState.Loading<Long>())
        val result = hospitalRepository.insertHospital(hospital.map { it.toHospitalEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}