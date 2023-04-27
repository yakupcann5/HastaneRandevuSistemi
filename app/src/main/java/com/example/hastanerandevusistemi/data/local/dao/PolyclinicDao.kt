package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.PolyclinicEntity

@Dao
interface PolyclinicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPolyclinic(polyclinicEntityList: List<PolyclinicEntity>): List<Long>

    @Query("SELECT * FROM polyclinic")
    suspend fun getAllPolyclinic(): List<PolyclinicEntity>
}