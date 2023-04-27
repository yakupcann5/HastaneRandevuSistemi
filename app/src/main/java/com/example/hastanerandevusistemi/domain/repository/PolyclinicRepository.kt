package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.PolyclinicEntity

interface PolyclinicRepository {
    suspend fun getAllPolyclinic(): List<PolyclinicEntity>
    suspend fun insertPolyclinic(polyclinicEntity: List<PolyclinicEntity>): List<Long>
}