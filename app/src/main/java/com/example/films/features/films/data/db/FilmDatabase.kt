package com.example.films.features.films.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.films.features.films.domain.model.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract val databaseDao: FilmDao
}