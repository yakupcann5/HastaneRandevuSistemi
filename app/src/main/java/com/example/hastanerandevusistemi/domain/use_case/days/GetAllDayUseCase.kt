package com.example.hastanerandevusistemi.domain.use_case.days

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.DaysEntity
import com.example.hastanerandevusistemi.domain.repository.DaysRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllDayUseCase @Inject constructor(var daysRepository: DaysRepository) {
    operator fun invoke(doctorId: Int) = flow {
        emit(RequestState.Loading<List<DaysEntity>>())
        val result = daysRepository.getDays(doctorId)
        emit(RequestState.Success<List<DaysEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<DaysEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}