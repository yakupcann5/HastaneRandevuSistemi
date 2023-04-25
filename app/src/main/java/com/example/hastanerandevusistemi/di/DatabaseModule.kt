package com.example.hastanerandevusistemi.di

import android.content.Context
import androidx.room.Room
import com.example.hastanerandevusistemi.data.AppDatabase
import com.example.hastanerandevusistemi.data.local.dao.UserDao
import com.example.hastanerandevusistemi.data.local.repository.UserRepositoryImpl
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
}