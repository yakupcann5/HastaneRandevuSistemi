package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hastanerandevusistemi.domain.model.Hastane
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hospital")
data class HospitalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("ilceId")
    val ilceId: Int? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)

