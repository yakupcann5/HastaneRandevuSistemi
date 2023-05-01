package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity

data class Appointment(
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
) : java.io.Serializable {
    fun toAppointmentEntity(): AppointmentEntity {
        return AppointmentEntity(
            userId = this.userId,
            cityId = this.cityId,
            cityName = this.cityName,
            hospitalId = this.hospitalId,
            hospitalName = this.hospitalName,
            departmentId = this.departmentId,
            departmentName = this.departmentName,
            doctorId = this.doctorId,
            doctorName = this.doctorName,
            dayId = this.dayId,
            dayName = this.dayName,
            hourId = this.hourId,
            hourName = this.hourName
        )
    }
}
