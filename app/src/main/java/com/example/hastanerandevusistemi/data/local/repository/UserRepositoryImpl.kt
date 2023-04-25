package com.example.hastanerandevusistemi.data.local.repository

import com.example.hastanerandevusistemi.data.local.dao.UserDao
import com.example.hastanerandevusistemi.data.local.entities.UserEntity
import com.example.hastanerandevusistemi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(var userDao: UserDao) : UserRepository {
    override suspend fun insertChild(user: UserEntity): Long {
        return userDao.insertUser(user)
    }

    override suspend fun getAllUser(): List<UserEntity> {
        return userDao.getAllUser()
    }

    override suspend fun getUserById(id: Int): UserEntity {
        return userDao.getUserById(id)
    }
}