package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.PolyclinicDao
import com.example.hastanerandevusistemi.data.local.entities.PolyclinicEntity
import com.example.hastanerandevusistemi.domain.repository.PolyclinicRepository
import javax.inject.Inject

class PolyclinicRepositoryImpl @Inject constructor(private val polyclinicDao: PolyclinicDao): PolyclinicRepository {
    override suspend fun getAllPolyclinic(): List<PolyclinicEntity> {
        return polyclinicDao.getAllPolyclinic()
    }

    override suspend fun insertPolyclinic(polyclinicEntity: List<PolyclinicEntity>): List<Long> {
        return polyclinicDao.insertPolyclinic(polyclinicEntity)
    }
}