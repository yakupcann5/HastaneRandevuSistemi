package com.example.hastanerandevusistemi.domain.use_case.city

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.CityEntity
import com.example.hastanerandevusistemi.domain.repository.CityRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCityUseCase @Inject constructor(
    private var repository: CityRepository
) {
    operator fun invoke() = flow {
        emit(RequestState.Loading<List<CityEntity>>())
        val result = repository.getAllCity()
        emit(RequestState.Success<List<CityEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<CityEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}