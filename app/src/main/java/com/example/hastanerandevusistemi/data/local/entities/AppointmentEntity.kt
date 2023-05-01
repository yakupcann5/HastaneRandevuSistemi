package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userId: Int? = null,
    val cityId: Int? = null,
    val hospitalId: Int? = null,
    val departmentId: Int? = null,
    val doctorId: Int? = null,
    val dayId: Int? = null,
    val hourId: Int? = null
)
