package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.DoctorEntity

data class Doktor(
    val gunler: List<Gunler>,
    val poliklinikId: Int,
    val name: String,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toDoctorEntity(): DoctorEntity {
        return DoctorEntity(
            name = this.name,
            poliklinikId = this.poliklinikId,
            text = this.text,
            value = this.value
        )
    }
}