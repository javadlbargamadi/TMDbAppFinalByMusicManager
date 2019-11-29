package com.example.tmdbappfinalbymusicmanager.movieFavorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdbappfinalbymusicmanager.R

/**
 * A simple [Fragment] subclass.
 */
class MovieFavoritesFragment : Fragment() {
    companion object {
        fun newInstance() =
            MovieFavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorites, container, false)
    }


}
