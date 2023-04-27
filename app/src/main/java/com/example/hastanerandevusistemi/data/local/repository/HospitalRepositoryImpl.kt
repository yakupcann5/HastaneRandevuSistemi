package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.HospitalDao
import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity
import com.example.hastanerandevusistemi.domain.repository.HospitalRepository
import javax.inject.Inject

class HospitalRepositoryImpl @Inject constructor(private val hospitalRepository: HospitalDao): HospitalRepository {
    override suspend fun getAllHospital(): List<HospitalEntity> {
        return hospitalRepository.getAllHospital()
    }

    override suspend fun insertHospital(hospital: List<HospitalEntity>): List<Long> {
        return hospitalRepository.insertHospital(hospital)
    }
}