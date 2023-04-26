package com.example.hastanerandevusistemi.domain.use_case.district

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.District
import com.example.hastanerandevusistemi.domain.repository.CityRepository
import com.example.hastanerandevusistemi.domain.repository.DistrictRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveDistrictUseCase @Inject constructor(private val districtRepository: DistrictRepository) {
    operator fun invoke(district : List<District>) = flow {
        emit(RequestState.Loading<Long>())
        val result = districtRepository.insertDistrict(district.map { it.toDistrictEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}