package com.example.movies.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.ActivityMoviesBinding
import com.example.movies.model.dtos.MoviesDTO
import com.example.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModel()

    private lateinit var binding: ActivityMoviesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpObservers()
        swipeRefreshSetup()
        refreshMovies()

    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolBar)

        binding.favoritesButton.setOnClickListener {
            startActivity(Intent(this, FavoriteMoviesActivity::class.java))
        }
    }

    private fun setUpObservers() {
        viewModel.movies.observe(this) { updateMoviesList(it) }

        viewModel.error.observe(this) { if(it) showError() }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateMoviesList(moviesDTO: MoviesDTO) {

        val context = this
        val gridLayoutManager = GridLayoutManager(context, 2)
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
        viewModel.upDateFavoriteList(id, getSharedPreferences(viewModel.favoritesKey, MODE_PRIVATE))
    }

    fun isMovieFavorite(id: String): Boolean {
        return viewModel.isMovieFavorite(id, getSharedPreferences(viewModel.favoritesKey, MODE_PRIVATE))
    }
}