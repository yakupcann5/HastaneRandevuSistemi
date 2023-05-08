package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.HourEntity

@Dao
interface HourDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHour(hourEntity: List<HourEntity>): List<Long>

    @Query("SELECT * FROM hour WHERE gunId = :dayId")
    suspend fun getAllHour(dayId: Int): List<HourEntity>

    @Query("UPDATE hour SET selected = 0 WHERE gunId = :dayId AND value = :hourId")
    suspend fun updateHour(dayId: Int, hourId: Int)
}