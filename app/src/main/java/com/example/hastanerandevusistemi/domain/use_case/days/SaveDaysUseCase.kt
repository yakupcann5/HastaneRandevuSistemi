package com.example.hastanerandevusistemi.domain.use_case.days

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.DaysEntity
import com.example.hastanerandevusistemi.domain.model.Gunler
import com.example.hastanerandevusistemi.domain.repository.DaysRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveDaysUseCase @Inject constructor(private val daysRepository: DaysRepository) {
    operator fun invoke(days: List<Gunler>) = flow {
        emit(RequestState.Loading<Long>())
        val result = daysRepository.insertDays(days.map { it.toDaysEntity() })
        emit(RequestState.Success(result))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}