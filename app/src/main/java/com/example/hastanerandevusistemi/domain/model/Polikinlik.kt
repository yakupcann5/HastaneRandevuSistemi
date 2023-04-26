package com.example.hastanerandevusistemi.domain.model

data class Polikinlik(
    val doktor: List<Doktor>,
    val hastaneId: Int,
    val text: String,
    val value: Int
)