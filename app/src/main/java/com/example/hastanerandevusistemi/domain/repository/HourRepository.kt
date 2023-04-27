package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.HourEntity

interface HourRepository {
    suspend fun getAllHour(): List<HourEntity>
    suspend fun insertHour(hour: List<HourEntity>): List<Long>
}