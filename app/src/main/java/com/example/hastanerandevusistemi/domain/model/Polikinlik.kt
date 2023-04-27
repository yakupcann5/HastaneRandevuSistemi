package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.PolyclinicEntity

data class Polikinlik(
    val doktor: List<Doktor>,
    val hastaneId: Int,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toPolyclinicEntity(): PolyclinicEntity {
        return PolyclinicEntity(
            hastaneId = this.hastaneId,
            text = this.text,
            value = this.value
        )
    }
}