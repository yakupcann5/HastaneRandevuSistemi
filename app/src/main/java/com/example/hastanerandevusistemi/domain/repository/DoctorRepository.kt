package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.DoctorEntity

interface DoctorRepository {
    suspend fun getDoctors(): List<DoctorEntity>
    suspend fun insertDoctors(doctors: List<DoctorEntity>): List<Long>
}