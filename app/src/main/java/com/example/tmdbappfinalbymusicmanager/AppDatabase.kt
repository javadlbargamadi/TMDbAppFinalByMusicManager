package com.example.tmdbappfinalbymusicmanager

import androidx.room.Database
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [MovieDatabaseEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListDataConverter::class)
abstract class AppDatabase {

    abstract fun moviesDAO(): MoviesDAO
}
