package com.example.hastanerandevusistemi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hastanerandevusistemi.data.local.dao.*
import com.example.hastanerandevusistemi.data.local.entities.*

@Database(
    entities = [
        UserEntity::class,
        CityEntity::class,
        DistrictEntity::class,
        HospitalEntity::class,
        PolyclinicEntity::class,
        DoctorEntity::class,
        DaysEntity::class,
        HourEntity::class,
        AppointmentEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cityDao(): CityDao
    abstract fun districtDao(): DistrictDao
    abstract fun hospitalDao(): HospitalDao
    abstract fun polyclinicDao(): PolyclinicDao
    abstract fun doctorDao(): DoctorDao
    abstract fun daysDao(): DaysDao
    abstract fun hourDao(): HourDao
    abstract fun appointmentDao(): AppointmentDao
}