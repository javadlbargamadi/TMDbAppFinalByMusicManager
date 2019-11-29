package com.example.tmdbappfinalbymusicmanager

import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val baseApplication: BaseApplicaiton) {

    @Singleton
    @Provides
    fun database(): AppDatabase {
        return Room.databaseBuilder(
            baseApplication.applicationContext,
            AppDatabase::class.java,
            "Movies.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMoviesDAO(appDatabase: AppDatabase): MoviesDAO {
        return appDatabase.moviesDAO()
    }
}
