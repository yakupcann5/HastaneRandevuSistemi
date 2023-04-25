package com.example.hastanerandevusistemi.domain.repository

import com.example.hastanerandevusistemi.data.local.entities.UserEntity

interface UserRepository {
    suspend fun insertChild(user: UserEntity): Long
    suspend fun getAllUser(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
}