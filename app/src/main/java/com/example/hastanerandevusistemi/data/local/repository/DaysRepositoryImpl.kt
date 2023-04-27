package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.DaysDao
import com.example.hastanerandevusistemi.data.local.entities.DaysEntity
import com.example.hastanerandevusistemi.domain.repository.DaysRepository
import javax.inject.Inject

class DaysRepositoryImpl @Inject constructor(private val daysDao: DaysDao): DaysRepository {
    override suspend fun getDays(): List<DaysEntity> {
        return daysDao.getDays()
    }

    override suspend fun insertDays(daysEntity: List<DaysEntity>): List<Long> {
        return daysDao.insertDays(daysEntity)
    }

}