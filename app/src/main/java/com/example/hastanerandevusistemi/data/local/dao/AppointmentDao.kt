package com.example.hastanerandevusistemi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity

@Dao
interface AppointmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointmentEntity: List<AppointmentEntity>): List<Long>

    @Query("SELECT * FROM appointment WHERE userId = :userId")
    suspend fun getAllAppointment(userId: Int): List<AppointmentEntity>
}