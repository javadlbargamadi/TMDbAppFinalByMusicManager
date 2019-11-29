package com.example.tmdbappfinalbymusicmanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieDatabaseEntity: MovieDatabaseEntity): Completable

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Single<List<MovieDatabaseEntity>>

    @Query("SELECT * FROM movies WHERE movie_name = :movieName and movie_date = :movieDate LIMIT 1")
    fun getSpecificMovie(movieName: String, movieDate: String): Single<MovieDatabaseEntity>

    @Query("SELECT COUNT(*)>0 from movies WHERE movie_name = :movieName and movie_date = :movieDate")
    fun doesMovieExists(movieName: String, movieDate: String): Single<Boolean>

    @Query("DELETE from movies where movie_name = :movieName and movie_date = :movieDate")
    fun removeMovie(movieName: String, movieDate: String): Completable
}
