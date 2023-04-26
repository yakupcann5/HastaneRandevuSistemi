package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.DistrictEntity

data class District(
    val hastane: List<Hastane>,
    val ilId: Int,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toDistrictEntity(): DistrictEntity {
        return DistrictEntity(
            ilId = this.ilId,
            text = this.text,
            value = this.value
        )
    }
}