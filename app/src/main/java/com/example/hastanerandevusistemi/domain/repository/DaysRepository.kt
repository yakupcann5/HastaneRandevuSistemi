package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.DaysEntity

interface DaysRepository {
    suspend fun getDays(): List<DaysEntity>
    suspend fun insertDays(daysEntity: List<DaysEntity>): List<Long>
}