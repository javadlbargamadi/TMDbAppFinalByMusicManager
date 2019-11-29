package com.example.tmdbappfinalbymusicmanager.movieSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbappfinalbymusicmanager.DataRepository
import com.example.tmdbappfinalbymusicmanager.ViewNotifierEnums
import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.Result
import io.reactivex.disposables.CompositeDisposable
import java.util.logging.Logger

class MovieSearchViewModel(private val dataRepository: DataRepository) : BaseViewModel() {
    private val composit = CompositeDisposable()
    private val moviesResponse = MutableLiveData<List<Result>>()
    private var page = 1
    private var shouldLoadMore = true
    private var list = arrayListOf<Result>()

    fun onUserSearchedMovie(movieName: String, apiKey: String, isLoadMore: Boolean) {

        prepareDataToSearch(isLoadMore)

        viewNotifier.value = ViewNotifierEnums.SHOW_LOADING

        if (composit.size() > 0)
            composit.clear()

        composit.add(
            dataRepository.getMovieName(movieName, page, apiKey)
                .subscribe({
                    if (it.results)
                        shouldLoadMore = false

                    list.addAll(it.results)
                    moviesResponse.value = list
                    viewNotifier.value = ViewNotifierEnums.HIDE_LOADING
                }, {
                    Logger.d(it.message)
                    viewNotifier.value = ViewNotifierEnums.HIDE_LOADING
                    viewNotifier.value = ViewNotifierEnums.ERROR_GETTING_DATA
                })
        )
    }

    private fun prepareDataToSearch(isLoadMore: Boolean) {
        if (isLoadMore && shouldLoadMore) {
            page++
        } else {
            page = 1
            list.clear()
            shouldLoadMore = true
        }
    }

    fun getMovieResponse(): LiveData<List<Result>> = moviesResponse

    override fun onCleared() {
        composit.dispose()
        super.onCleared()
    }

}