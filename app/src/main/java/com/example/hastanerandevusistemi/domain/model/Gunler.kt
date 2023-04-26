package com.example.hastanerandevusistemi.domain.model

data class Gunler(
    val doktorId: Int,
    val saatler: List<Saatler>,
    val text: String,
    val value: Int
)