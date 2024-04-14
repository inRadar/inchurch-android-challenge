package com.example.movies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.model.dtos.MoviesDTO
import com.example.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObservers()
        swipeRefreshSetup()
        refreshMovies()

    }

    private fun setUpObservers() {
        viewModel.movies.observe(this) { updateMoviesList(it) }

        viewModel.error.observe(this) { if(it) showError() }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateMoviesList(moviesDTO: MoviesDTO) {

        val gridLayoutManager = GridLayoutManager(this, 2)
        val context = this
        with(binding){
            movieList.layoutManager = gridLayoutManager
            movieList.adapter = MoviesAdapter(moviesDTO, context)
            movieList.adapter?.notifyDataSetChanged()

            errorLayout.visibility = INVISIBLE
            movieList.visibility = VISIBLE

            swipeRefresh.isRefreshing = false
        }

    }

    private fun swipeRefreshSetup() = with(binding.swipeRefresh) {
        setOnRefreshListener {
            refreshMovies()
        }
    }

    private fun refreshMovies() {
        binding.swipeRefresh.isRefreshing = true
        viewModel.getMovies()
    }

    private fun showError() {
        with(binding) {
            errorLayout.visibility = VISIBLE
            movieList.visibility = INVISIBLE

            binding.swipeRefresh.isRefreshing = false
        }
    }

    fun upDateFavoriteList(id: String) {
        viewModel.upDateFavoriteList(id, getPreferences(MODE_PRIVATE))
    }

    fun isMovieFavorite(id: String): Boolean {
        return viewModel.isMovieFavorite(id, getPreferences(MODE_PRIVATE))
    }
}