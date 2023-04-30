package com.example.hastanerandevusistemi.domain.use_case.polyclinic

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity
import com.example.hastanerandevusistemi.data.local.entities.PolyclinicEntity
import com.example.hastanerandevusistemi.domain.repository.HospitalRepository
import com.example.hastanerandevusistemi.domain.repository.PolyclinicRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPoliklinikUseCase @Inject constructor(var polyclinicRepository: PolyclinicRepository) {
    operator fun invoke(hospitalId: Int) = flow {
        emit(RequestState.Loading<List<PolyclinicEntity>>())
        val result = polyclinicRepository.getAllPolyclinic(hospitalId)
        emit(RequestState.Success<List<PolyclinicEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<PolyclinicEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}