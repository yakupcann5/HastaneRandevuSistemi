package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.HourEntity
import com.example.hastanerandevusistemi.domain.model.Appointment

interface HourRepository {
    suspend fun getAllHour(datId: Int): List<HourEntity>
    suspend fun insertHour(hour: List<HourEntity>): List<Long>
    suspend fun updateHour(dayId : Int, hourId : Int)
}