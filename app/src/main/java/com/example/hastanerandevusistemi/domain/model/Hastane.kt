package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity

data class Hastane(
    val ilceId: Int,
    val polikinlik: List<Polikinlik>,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toHospitalEntity(): HospitalEntity {
        return HospitalEntity(
            ilceId = this.ilceId,
            text = this.text,
            value = this.value
        )
    }
}