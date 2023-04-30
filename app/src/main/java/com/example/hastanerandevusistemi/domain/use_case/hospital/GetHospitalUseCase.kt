package com.example.hastanerandevusistemi.domain.use_case.hospital

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity
import com.example.hastanerandevusistemi.domain.repository.HospitalRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHospitalUseCase @Inject constructor(var hospitalRepository: HospitalRepository) {
    operator fun invoke(ilçeId: Int) = flow {
        emit(RequestState.Loading<List<HospitalEntity>>())
        val result = hospitalRepository.getAllHospital(ilçeId)
        emit(RequestState.Success<List<HospitalEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<HospitalEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}