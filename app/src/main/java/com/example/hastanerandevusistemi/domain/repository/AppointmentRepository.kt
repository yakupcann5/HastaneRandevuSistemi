package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity

interface AppointmentRepository {
    suspend fun getAllAppointment(userId: Int): List<AppointmentEntity>
    suspend fun insertAppointment(appointmentEntity: List<AppointmentEntity>): List<Long>
}