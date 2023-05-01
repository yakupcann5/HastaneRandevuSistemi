package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userId: Int? = null,
    val cityId: Int? = null,
    val cityName: String? = null,
    val hospitalId: Int? = null,
    val hospitalName: String? = null,
    val departmentId: Int? = null,
    val departmentName: String? = null,
    val doctorId: Int? = null,
    val doctorName: String? = null,
    val dayId: Int? = null,
    val dayName: String? = null,
    val hourId: Int? = null,
    val hourName: String? = null
)