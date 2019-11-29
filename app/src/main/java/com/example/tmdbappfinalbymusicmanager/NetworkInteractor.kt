package com.example.tmdbappfinalbymusicmanager

import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.FindMoviesByName
import com.example.tmdbappfinalbymusicmanager.retrofit.RetrofitMainInterface
import io.reactivex.Observable
import javax.inject.Inject

class NetworkInteractor @Inject constructor(private val retrofitInterface: RetrofitMainInterface) {

    fun getMoviesName(movieName: String, page: Int, apiKey: String): Observable<FindMoviesByName> {
        return retrofitInterface.findMoviesByName(movieName, page, apiKey)
    }

}
