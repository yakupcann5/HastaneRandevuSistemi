package com.example.hastanerandevusistemi.domain.model

import com.example.hastanerandevusistemi.data.local.entities.CityEntity

data class City(
    val districts: List<District>,
    val text: String,
    val value: Int
) : java.io.Serializable{
    fun toCityEntity(): CityEntity {
        return CityEntity(
            value = this.value,
            name = this.text
        )
    }
}