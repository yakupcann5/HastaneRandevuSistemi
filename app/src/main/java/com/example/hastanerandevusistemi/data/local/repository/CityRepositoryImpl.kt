package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.CityDao
import com.example.hastanerandevusistemi.data.local.entities.CityEntity
import com.example.hastanerandevusistemi.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val cityDao: CityDao) : CityRepository {
    override suspend fun getAllCity(): List<CityEntity> {
        return cityDao.getAllCity()
    }

    override suspend fun insertCity(city: List<CityEntity>): List<Long> {
        return cityDao.insertCity(city)
    }

    override suspend fun getCount(): Int {
        return cityDao.getCount()
    }
}