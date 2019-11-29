package com.example.tmdbappfinalbymusicmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "movie_name") var movieName: String,
    @ColumnInfo(name = "movie_date") var movieDate: String
)
