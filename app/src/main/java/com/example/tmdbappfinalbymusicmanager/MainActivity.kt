package com.example.tmdbappfinalbymusicmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.tmdbappfinalbymusicmanager.Consts.Companion.CURRENT_FRAGMENT
import com.example.tmdbappfinalbymusicmanager.movieFavorites.MovieFavoritesFragment
import com.example.tmdbappfinalbymusicmanager.movieHome.MovieHomeFragment
import com.example.tmdbappfinalbymusicmanager.movieSearch.MovieSearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var movieHomeFragment: MovieHomeFragment? = null
    private var movieSearchFragment: MovieSearchFragment? = null
    private var movieFavoritesFragment: MovieFavoritesFragment? = null
    private var aboutFragment: AboutFragment? = null
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            setUpFragments()
            addFragmentsToLayout()
        } else
            retainFragments(savedInstanceState.getString(CURRENT_FRAGMENT))

        setUpBottomNav()
    }

    private fun addFragmentsToLayout() {
        movieHomeFragment?.let {
            FragmentUtils.addAndHideFragments(
                supportFragmentManager,
                R.id.mainActivityFrameLayout,
                it
            )
        }
        movieSearchFragment?.let {
            FragmentUtils.addAndHideFragments(
                supportFragmentManager,
                R.id.mainActivityFrameLayout,
                it
            )
        }
        movieFavoritesFragment?.let {
            FragmentUtils.addAndHideFragments(
                supportFragmentManager,
                R.id.mainActivityFrameLayout,
                it
            )
        }
        aboutFragment?.let {
            FragmentUtils.addAndHideFragments(
                supportFragmentManager,
                R.id.mainActivityFrameLayout,
                it
            )
        }
        displayFirstTab()
    }

    private fun displayFirstTab() {
        Handler().postDelayed({ displayFragments(movieHomeFragment) }, 200)
    }

    private fun retainFragments(fragment: String?) {
        movieHomeFragment =
            supportFragmentManager.findFragmentByTag(MovieHomeFragment::class.java.simpleName) as? MovieHomeFragment
        movieSearchFragment =
            supportFragmentManager.findFragmentByTag(MovieSearchFragment::class.java.simpleName) as? MovieSearchFragment
        movieFavoritesFragment =
            supportFragmentManager.findFragmentByTag(MovieFavoritesFragment::class.java.simpleName) as? MovieFavoritesFragment
        aboutFragment =
            supportFragmentManager.findFragmentByTag(AboutFragment::class.java.simpleName) as? AboutFragment

        when (fragment) {
            MovieHomeFragment::class.java.simpleName -> currentFragment = movieHomeFragment
            MovieSearchFragment::class.java.simpleName -> currentFragment = movieSearchFragment
            MovieFavoritesFragment::class.java.simpleName -> currentFragment =
                movieFavoritesFragment
            AboutFragment::class.java.simpleName -> currentFragment = aboutFragment
        }
    }

    private fun setUpFragments() {
        movieHomeFragment = MovieHomeFragment.newInstance()
        movieSearchFragment = MovieSearchFragment.newInstance()
        movieFavoritesFragment = MovieFavoritesFragment.newInstance()
        aboutFragment = AboutFragment.newInstance()
    }

    private fun setUpBottomNav() {
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            showFragment(it.itemId)
            true
        }
    }

    private fun showFragment(id: Int) {
        when (id) {
            R.id.home_item -> displayFragments(movieHomeFragment)
            R.id.search_item -> displayFragments(movieSearchFragment)
            R.id.favorites_item -> displayFragments(movieFavoritesFragment)
            R.id.about_item -> displayFragments(aboutFragment)

        }
    }

    private fun displayFragments(clickedFragment: Fragment?) {
        val manager = supportFragmentManager.beginTransaction()

        if (clickedFragment != null)
            if (currentFragment == clickedFragment)
                return
            else if (clickedFragment.isAdded && currentFragment == null) {
                manager.show(clickedFragment)
            } else if (clickedFragment.isAdded && currentFragment != null) {
                manager.hide(currentFragment!!)
                manager.show(clickedFragment)
            }
        manager.commit()
        currentFragment = clickedFragment
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURRENT_FRAGMENT, currentFragment?.tag)
        super.onSaveInstanceState(outState)
    }
}

