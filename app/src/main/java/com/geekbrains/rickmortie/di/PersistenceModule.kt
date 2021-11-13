package com.geekbrains.rickmortie.di

import androidx.room.Room
import com.geekbrains.rickmortie.App
import com.geekbrains.rickmortie.database.AppDatabase
import com.geekbrains.rickmortie.database.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: App,
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "rickandmortie.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }
}