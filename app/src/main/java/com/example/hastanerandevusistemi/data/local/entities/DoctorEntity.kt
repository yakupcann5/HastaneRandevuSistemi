package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doctor")
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("poliklinikId")
    val poliklinikId: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)
