package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.DaysEntity

@Dao
interface DaysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(daysEntity: List<DaysEntity>): List<Long>

    @Query("SELECT * FROM days WHERE doktorId = :doctorId")
    suspend fun getDays(doctorId: Int): List<DaysEntity>
}