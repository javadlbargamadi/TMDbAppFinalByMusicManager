package com.example.tmdbappfinalbymusicmanager.retrofit

import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.FindMoviesByName
import com.example.tmdbappfinalbymusicmanager.pojos.FindTrendingMoviesDailyClass.FindTrendingMoviesDaily
import com.example.tmdbappfinalbymusicmanager.pojos.FindTrendingMoviesWeekly.FindTrendingMoviesWeekly
import com.example.tmdbappfinalbymusicmanager.pojos.FindTrendingTvShowsDailyClass.FindTrendingTvShowsDaily
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitMainInterface {

    @GET("movie?api_key=7ac72bd9edccc294270b0fb0981b7fb9&language=en-US&page=1&include_adult=true")
    fun findMoviesByName(
        @Query("query") movieName: String, page: Int,
        apiKey: String
    ): Observable<FindMoviesByName>

    @GET("trending/movie/day?api_key=7ac72bd9edccc294270b0fb0981b7fb9")
    fun findTrendingMoviesDaily(@Path("movie") movie: String = "movie", @Path("day") day: String = "day"): Observable<FindTrendingMoviesDaily>

    @GET("trending/tv/day?api_key=7ac72bd9edccc294270b0fb0981b7fb9")
    fun findTrendingMoviesWeekly(@Path("tv") tv: String = "tv", @Path("day") day: String = "day"): Observable<FindTrendingTvShowsDaily>

    @GET("trending/person/day?api_key=7ac72bd9edccc294270b0fb0981b7fb9")
    fun findTrendingPeopleDaily(@Path("tv") tv: String = "tv", @Path("day") day: String = "day"): Observable<FindTrendingMoviesWeekly>
}