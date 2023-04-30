package com.example.hastanerandevusistemi.domain.use_case.district

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.DistrictEntity
import com.example.hastanerandevusistemi.domain.repository.DistrictRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllDistrictUseCase @Inject constructor(
    private val districtRepository: DistrictRepository
) {
    operator fun invoke(ilId: Int) = flow {
        emit(RequestState.Loading<List<DistrictEntity>>())
        val result = districtRepository.getAllDistrict(ilId)
        emit(RequestState.Success<List<DistrictEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<DistrictEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}