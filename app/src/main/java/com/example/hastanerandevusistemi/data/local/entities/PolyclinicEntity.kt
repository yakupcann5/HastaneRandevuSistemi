package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "polyclinic")
data class PolyclinicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("hastaneId")
    val hastaneId: Int? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)
