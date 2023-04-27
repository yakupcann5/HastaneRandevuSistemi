package com.example.hastanerandevusistemi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hastanerandevusistemi.data.local.dao.CityDao
import com.example.hastanerandevusistemi.data.local.dao.DistrictDao
import com.example.hastanerandevusistemi.data.local.dao.HospitalDao
import com.example.hastanerandevusistemi.data.local.dao.UserDao
import com.example.hastanerandevusistemi.data.local.entities.CityEntity
import com.example.hastanerandevusistemi.data.local.entities.DistrictEntity
import com.example.hastanerandevusistemi.data.local.entities.HospitalEntity
import com.example.hastanerandevusistemi.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, CityEntity::class, DistrictEntity::class, HospitalEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cityDao(): CityDao
    abstract fun districtDao(): DistrictDao
    abstract fun hospitalDao(): HospitalDao
}