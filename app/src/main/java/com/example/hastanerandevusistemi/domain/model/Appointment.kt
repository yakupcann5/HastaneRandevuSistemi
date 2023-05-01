package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.AppointmentEntity

data class Appointment(
    val userId: Int? = null,
    val cityId: Int? = null,
    val hospitalId: Int? = null,
    val departmentId: Int? = null,
    val doctorId: Int? = null,
    val dayId: Int? = null,
    val hourId: Int? = null
): java.io.Serializable {
    fun toAppointmentEntity(): AppointmentEntity {
        return AppointmentEntity(
            userId = this.userId,
            cityId = this.cityId,
            hospitalId = this.hospitalId,
            departmentId = this.departmentId,
            doctorId = this.doctorId,
            dayId = this.dayId,
            hourId = this.hourId
        )
    }
}
