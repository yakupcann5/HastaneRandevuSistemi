package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.AppointmentDao
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity
import com.example.hastanerandevusistemi.domain.repository.AppointmentRepository
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(var appointmentDao: AppointmentDao) :
    AppointmentRepository {
    override suspend fun getAllAppointment(userId: Int): List<AppointmentEntity> {
        return appointmentDao.getAllAppointment(userId)
    }

    override suspend fun insertAppointment(appointmentEntity: List<AppointmentEntity>): List<Long> {
        return appointmentDao.insertAppointment(appointmentEntity)
    }
}