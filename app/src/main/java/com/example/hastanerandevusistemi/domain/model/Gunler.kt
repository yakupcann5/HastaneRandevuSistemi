package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.DaysEntity

data class Gunler(
    val doktorId: Int,
    val saatler: List<Saatler>,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toDaysEntity(): DaysEntity {
        return DaysEntity(
            doktorId = this.doktorId,
            text = this.text,
            value = this.value
        )
    }
}