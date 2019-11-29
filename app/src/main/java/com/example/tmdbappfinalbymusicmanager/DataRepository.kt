package com.example.tmdbappfinalbymusicmanager

import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.FindMoviesByName
import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.Result
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataRepository @Inject constructor() {

    private var network: NetworkInteractor
    private var database: MoviesDAO

    init {
        val component = DaggerDataProviderComponent.builder()
            .dataProvidersComponent(BaseApplicaiton.getDataProviderComponent()).build()

        network = component.getNetworkInteractor()
        database = component.getDbInteractor()
    }

    fun getMovieName(movieName: String, page: Int, apiKey: String): Observable<FindMoviesByName> {
        return network.getMoviesName(movieName, page, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieDetails(
        movieName: String
        apiKey: String
        offline: Boolean
    ): Single<MovieDatabaseEntity> {

        if (offline)
            return database.getSpecificMovie(movieName, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        else
            return network.getMovieDetails(movieName, apiKey)
                .map {
                    val movies = arrayListOf<String>()
                    it.forEach {
                        //TODO : complete it.****
//                        movies.add(it.)
                    }
                    //TODO : complete it.****
//                    val image = it.
                    MovieDatabaseEntity(null,)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllSavedMovies(): Single<List<MovieDatabaseEntity>> {
        return database.getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveMovie(movieDatabaseEntity: MovieDatabaseEntity): Completable {
        return database.saveMovie(movieDatabaseEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun doesMovieExists(movieName: String, movieDate: String): Single<Boolean> {
        return database.doesMovieExists(movieName, movieDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun removeAlbum(movieName: String, movieDate: String): Completable {
        return database.removeMovie(movieName, movieDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
