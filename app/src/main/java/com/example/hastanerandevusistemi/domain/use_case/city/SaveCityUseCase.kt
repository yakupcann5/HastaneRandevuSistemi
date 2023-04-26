package com.example.hastanerandevusistemi.domain.use_case.city

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.domain.model.City
import com.example.hastanerandevusistemi.domain.repository.CityRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(city: List<City>) = flow {
        emit(RequestState.Loading<Long>())
        val result = cityRepository.insertCity(city.map { it.toCityEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}