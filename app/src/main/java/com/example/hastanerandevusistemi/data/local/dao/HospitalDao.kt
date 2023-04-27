package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity

@Dao
interface HospitalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHospital(hospitalEntity: List<HospitalEntity>): List<Long>

    @Query("SELECT * FROM hospital")
    suspend fun getAllHospital(): List<HospitalEntity>
}