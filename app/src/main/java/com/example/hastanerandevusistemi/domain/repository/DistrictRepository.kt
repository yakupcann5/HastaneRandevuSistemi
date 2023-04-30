package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.CityEntity
import com.example.hastanerandevusistemi.data.local.entities.DistrictEntity

interface DistrictRepository {
    suspend fun getAllDistrict(ilId: Int): List<DistrictEntity>
    suspend fun insertDistrict(district: List<DistrictEntity>): List<Long>
}