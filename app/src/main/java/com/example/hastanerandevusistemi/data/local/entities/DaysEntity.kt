package com.example.hastanerandevusistemi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hastanerandevusistemi.domain.model.Saatler
import com.google.gson.annotations.SerializedName

@Entity(tableName = "days")
data class DaysEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("doktorId")
    val doktorId: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("value")
    val value: Int
)