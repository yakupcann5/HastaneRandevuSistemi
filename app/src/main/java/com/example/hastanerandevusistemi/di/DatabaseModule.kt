package com.example.hastanerandevusistemi.di

import android.content.Context
import androidx.room.Room
import com.example.hastanerandevusistemi.data.AppDatabase
import com.example.hastanerandevusistemi.data.local.dao.*
import com.example.hastanerandevusistemi.data.local.repository.*
import com.example.hastanerandevusistemi.domain.repository.*
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

    @Provides
    fun providePolyclinicDao(appDatabase: AppDatabase): PolyclinicDao {
        return appDatabase.polyclinicDao()
    }

    @Provides
    fun providePolyclinicDbRepositoryImpl(dao: PolyclinicDao): PolyclinicRepository {
        return PolyclinicRepositoryImpl(dao)
    }

    @Provides
    fun provideDoctorDao(appDatabase: AppDatabase): DoctorDao {
        return appDatabase.doctorDao()
    }

    @Provides
    fun provideDoctorDbRepositoryImpl(dao: DoctorDao): DoctorRepository {
        return DoctorRepositoryImpl(dao)
    }

    @Provides
    fun provideCityRepositoryImpl(dao: DaysDao): DaysRepository {
        return DaysRepositoryImpl(dao)
    }

    @Provides
    fun provideDaysDao(appDatabase: AppDatabase): DaysDao {
        return appDatabase.daysDao()
    }

    @Provides
    fun provideDaysDbRepositoryImpl(dao: HourDao): HourRepository {
        return HourRepositoryImpl(dao)
    }

    @Provides
    fun provideHourDao(appDatabase: AppDatabase): HourDao {
        return appDatabase.hourDao()
    }

    @Provides
    fun provideHourDbRepositoryImpl(dao: AppointmentDao): AppointmentRepository {
        return AppointmentRepositoryImpl(dao)
    }

    @Provides
    fun provideAppointmentDao(appDatabase: AppDatabase): AppointmentDao {
        return appDatabase.appointmentDao()
    }
}