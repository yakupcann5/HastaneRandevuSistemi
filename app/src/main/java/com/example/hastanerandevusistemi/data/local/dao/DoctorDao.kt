package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.DoctorEntity
import com.example.hastanerandevusistemi.domain.model.Doktor
import javax.inject.Inject

@Dao
interface DoctorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctors(doctors: List<DoctorEntity>): List<Long>

    @Query("SELECT * FROM doctor WHERE poliklinikId = :departmentId")
    suspend fun getDoctors(departmentId: Int): List<DoctorEntity>
}