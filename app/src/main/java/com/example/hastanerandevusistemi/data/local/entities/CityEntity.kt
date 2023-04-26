package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("value")
    val value: Int? = null,
    @SerializedName("text")
    val name: String? = null
)