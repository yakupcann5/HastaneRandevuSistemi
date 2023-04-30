package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity

interface HospitalRepository {
    suspend fun getAllHospital(ilçeId: Int): List<HospitalEntity>
    suspend fun insertHospital(hospital: List<HospitalEntity>): List<Long>
}