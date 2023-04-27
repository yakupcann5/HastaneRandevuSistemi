package com.example.hastanerandevusistemi.domain.use_case.polyclinic

import com.example.hastanerandevusistemi.common.RequestState
import com.example.hastanerandevusistemi.data.local.entities.PolyclinicEntity
import com.example.hastanerandevusistemi.domain.model.Polikinlik
import com.example.hastanerandevusistemi.domain.repository.PolyclinicRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SavePolyclinicUseCase @Inject constructor(private val polyclinicRepository: PolyclinicRepository) {
    operator fun invoke(polyclinicEntity: List<Polikinlik>) = flow {
        emit(RequestState.Loading<Long>())
        val result =
            polyclinicRepository.insertPolyclinic(polyclinicEntity.map { it.toPolyclinicEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}