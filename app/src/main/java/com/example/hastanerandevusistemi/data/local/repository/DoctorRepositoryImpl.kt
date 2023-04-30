package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.DoctorDao
import com.example.hastanerandevusistemi.data.local.entities.DoctorEntity
import com.example.hastanerandevusistemi.domain.model.Doktor
import com.example.hastanerandevusistemi.domain.repository.DoctorRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorDao: DoctorDao
) : DoctorRepository {
    override suspend fun getDoctors(departmentId: Int): List<DoctorEntity> {
        return doctorDao.getDoctors(departmentId)
    }


    override suspend fun insertDoctors(doctors: List<DoctorEntity>): List<Long> {
        return doctorDao.insertDoctors(doctors)
    }
}