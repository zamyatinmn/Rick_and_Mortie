package com.geekbrains.rickmortie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.geekbrains.rickmortie.model.Character


@Database(entities = [Character::class], version = 1, exportSchema = true)
@TypeConverters(LocationConverter::class, EpisodesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}
