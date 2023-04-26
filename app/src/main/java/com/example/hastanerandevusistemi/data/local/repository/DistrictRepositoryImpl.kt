package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.DistrictDao
import com.example.hastanerandevusistemi.data.local.entities.CityEntity
import com.example.hastanerandevusistemi.data.local.entities.DistrictEntity
import com.example.hastanerandevusistemi.domain.repository.DistrictRepository
import javax.inject.Inject

class DistrictRepositoryImpl @Inject constructor(private val districtDao: DistrictDao): DistrictRepository {
    override suspend fun getAllDistrict(): List<DistrictEntity> {
        return districtDao.getAllDistrict()
    }

    override suspend fun insertDistrict(district: List<DistrictEntity>): List<Long> {
        return districtDao.insertDistrict(district)
    }
}