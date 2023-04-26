package com.example.hastanerandevusistemi.domain.model

data class Hastane(
    val ilceId: Int,
    val polikinlik: List<Polikinlik>,
    val text: String,
    val value: Int
)