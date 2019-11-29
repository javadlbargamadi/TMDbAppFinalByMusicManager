package com.example.tmdbappfinalbymusicmanager.movieHome


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdbappfinalbymusicmanager.R

/**
 * A simple [Fragment] subclass.
 */
class MovieHomeFragment : Fragment() {
    companion object {
        fun newInstance() =
            MovieHomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_home, container, false)
    }


}
