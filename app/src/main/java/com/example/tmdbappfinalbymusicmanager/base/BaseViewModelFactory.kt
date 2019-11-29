package com.example.tmdbappfinalbymusicmanager.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbappfinalbymusicmanager.movieFavorites.MovieFavoritesViewModel
import com.example.tmdbappfinalbymusicmanager.movieHome.MovieHomeViewModel
import com.example.tmdbappfinalbymusicmanager.movieSearch.MovieSearchViewModel
import javax.inject.Inject

class BaseViewModelFactory @Inject constructor() :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val repository = DaggerDataRepositoryComponent.create().getDataRepository()
        return when {
            modelClass.isAssignableFrom(MovieSearchViewModel::class.java) -> MovieSearchViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(MovieHomeViewModel::class.java) -> MovieHomeViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(MovieFavoritesViewModel::class.java) -> MovieFavoritesViewModel(
                repository
            ) as T
            else -> throw IllegalStateException("No viewModel finded")
        }
    }
}
) {
}