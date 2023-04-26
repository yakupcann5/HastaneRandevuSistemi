package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    suspend fun getAllCity(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: List<CityEntity>): List<Long>
}