package com.example.hastanerandevusistemi.domain.use_case.doctor

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.DoctorEntity
import com.example.hastanerandevusistemi.domain.repository.DoctorRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllDoctorUseCase @Inject constructor(var doctorRepository: DoctorRepository) {
    operator fun invoke(departmentId: Int) = flow {
        emit(RequestState.Loading<List<DoctorEntity>>())
        val result = doctorRepository.getDoctors(departmentId)
        emit(RequestState.Success<List<DoctorEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<DoctorEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}