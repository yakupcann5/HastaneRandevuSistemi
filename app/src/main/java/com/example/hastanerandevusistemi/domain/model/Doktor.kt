package com.example.hastanerandevusistemi.domain.model

data class Doktor(
    val gunler: List<Gunler>,
    val name: String,
    val polikinlikId: Int,
    val poliklinikId: Int,
    val surname: String,
    val text: String,
    val value: Int
)