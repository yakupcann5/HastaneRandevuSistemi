package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.CityEntity

interface CityRepository {
    suspend fun getAllCity(): List<CityEntity>
    suspend fun insertCity(city: List<CityEntity>): List<Long>
}