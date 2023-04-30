package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.HourEntity

interface HourRepository {
    suspend fun getAllHour(datId: Int): List<HourEntity>
    suspend fun insertHour(hour: List<HourEntity>): List<Long>
}