package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.HourEntity

data class Saatler(
    val gunId: Int,
    val selected: Boolean,
    val text: String,
    val value: Int
): java.io.Serializable {
    fun toHourEntity(): HourEntity {
        return HourEntity(
            gunId = gunId,
            selected = selected,
            text = text,
            value = value
        )
    }
}