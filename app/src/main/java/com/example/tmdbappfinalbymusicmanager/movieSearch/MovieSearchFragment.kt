package com.example.tmdbappfinalbymusicmanager.movieSearch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbappfinalbymusicmanager.Consts
import com.example.tmdbappfinalbymusicmanager.R
import com.example.tmdbappfinalbymusicmanager.ViewNotifierEnums
import com.example.tmdbappfinalbymusicmanager.base.BaseViewModelFactory
import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.FindMoviesByName
import com.example.tmdbappfinalbymusicmanager.pojos.FindMoviesByNameClass.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_search.*

/**
 * A simple [Fragment] subclass.
 */
class MovieSearchFragment : Fragment() {
    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    private lateinit var viewModel: MovieSearchViewModel
    private lateinit var adapter: MovieSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = BaseViewModelFactory()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MovieSearchViewModel::class.java)

        init()
        setUpRecyclerView()
        showEmptyState()
    }


    private fun init() {
        searchFragmentEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> searchMovie()
            }
            false
        }

        searchFragmentSearchButton.setOnClickListener {
            searchMovie()
        }


        viewModel.getMovieResponse().observe(this, Observer {
            showRecycler(it)
        })

        viewModel.getViewNotifier().Observe(this, Observer {
            when (it) {
                ViewNotifierEnums.SHOW_LOADING -> searchFragmentProgressBar.visibility =
                    View.VISIBLE
                ViewNotifierEnums.HIDE_LOADING -> searchFragmentProgressBar.visibility =
                    View.INVISIBLE
                ViewNotifierEnums.ERROR_GETTING_DATA -> showTryAgain()
                else -> throw IllegalStateException("A notifier in not defined")
            }
        })
    }

    private fun showRecycler(findMoviesByName: List<Result>) {
        hideEmptyState()
        adapter.list = findMoviesByName
        adapter.notifyDataSetChanged()
    }

    private fun showTryAgain() {
        if (view != null)
            Snackbar.make(
                view!!,
                "Please try again",
                Snackbar.LENGTH_LONG
            ).setAction("Try Again") {
                searchMovie()
            }.show()
    }

    private fun searchMovie() {
        if (searchFragmentEditText.text.toString().isNotEmpty())
            viewModel.onUserSearchedMovie(
                searchFragmentEditText.text.toString(),
                Consts.API_KEY,
                false
            )
        else
            Toast.makeText(
                context,
                "Please enter something",
                Toast.LENGTH_LONG
            ).show()
    }

    private fun showEmptyState() {
        empty.visibility = View.VISIBLE
        searchFragmentRecyclerView.visibility = View.GONE
    }

    private fun setUpRecyclerView() {
        adapter = MovieSearchAdapter(emptyList())
        searchFragmentRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchFragmentRecyclerView.layoutManager = layoutManager

        searchFragmentRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastItem = layoutManager.findLastVisibleItemPosition()
                val total = layoutManager.itemCount
                if (total > 0)
                    if (total - 1 == lastItem)
                        viewModel.onUserSearchedMovie(
                            searchFragmentEditText.text.toString(),
                            Consts.API_KEY,
                            true
                        )
            }
        })
    }

    private fun hideEmptyState() {
        empty.visibility = View.GONE
        searchFragmentRecyclerView.visibility = View.VISIBLE
    }
}

