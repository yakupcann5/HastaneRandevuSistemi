package com.example.hastanerandevusistemi.di

import android.content.Context
import androidx.room.Room
import com.example.hastanerandevusistemi.data.AppDatabase
import com.example.hastanerandevusistemi.data.local.dao.CityDao
import com.example.hastanerandevusistemi.data.local.dao.DistrictDao
import com.example.hastanerandevusistemi.data.local.dao.HospitalDao
import com.example.hastanerandevusistemi.data.local.dao.UserDao
import com.example.hastanerandevusistemi.data.local.repository.CityRepositoryImpl
import com.example.hastanerandevusistemi.data.local.repository.DistrictRepositoryImpl
import com.example.hastanerandevusistemi.data.local.repository.HospitalRepositoryImpl
import com.example.hastanerandevusistemi.data.local.repository.UserRepositoryImpl
import com.example.hastanerandevusistemi.domain.repository.CityRepository
import com.example.hastanerandevusistemi.domain.repository.DistrictRepository
import com.example.hastanerandevusistemi.domain.repository.HospitalRepository
import com.example.hastanerandevusistemi.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "hastane_randevu_otomasyonu").build()
    }
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserDbRepositoryImpl(dao: UserDao): UserRepository {
        return UserRepositoryImpl(dao)
    }

    @Provides
    fun provideCityDao(appDatabase: AppDatabase): CityDao {
        return appDatabase.cityDao()
    }

    @Provides
    fun provideCityDbRepositoryImpl(dao: CityDao): CityRepository {
        return CityRepositoryImpl(dao)
    }

    @Provides
    fun provideDistrictDao(appDatabase: AppDatabase): DistrictDao {
        return appDatabase.districtDao()
    }

    @Provides
    fun provideDistrictDbRepositoryImpl(dao: DistrictDao): DistrictRepository {
        return DistrictRepositoryImpl(dao)
    }

    @Provides
    fun provideHospitalDao(appDatabase: AppDatabase): HospitalDao {
        return appDatabase.hospitalDao()
    }
    @Provides
    fun provideHospitalDbRepositoryImpl(dao: HospitalDao): HospitalRepository {
        return HospitalRepositoryImpl(dao)
    }
}