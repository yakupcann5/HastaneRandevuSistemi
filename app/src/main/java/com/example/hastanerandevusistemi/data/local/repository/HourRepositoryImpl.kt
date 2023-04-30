package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.HourDao
import com.example.hastanerandevusistemi.data.local.entities.HourEntity
import com.example.hastanerandevusistemi.domain.repository.HourRepository
import javax.inject.Inject

class HourRepositoryImpl @Inject constructor(private val hourDao: HourDao): HourRepository {
    override suspend fun getAllHour(dayId: Int): List<HourEntity> {
        return hourDao.getAllHour(dayId)
    }

    override suspend fun insertHour(hour: List<HourEntity>): List<Long> {
        return hourDao.insertHour(hour)
    }
}