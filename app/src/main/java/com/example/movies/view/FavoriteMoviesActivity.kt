package com.example.movies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivityFavoriteMoviesBinding
import com.example.movies.model.dtos.MovieDTO
import com.example.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteMoviesBinding

    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavoriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpList()
        setUpObservers()

    }

    private fun setUpList() {
        val ids = getSharedPreferences(viewModel.favoritesKey, MODE_PRIVATE).getStringSet(viewModel.favoritesKey, setOf())
        viewModel.getFavoriteMovies(ids as Set<String>)
    }

    private fun setUpObservers() {
        viewModel.favoriteMovies.observe(this) {
            populateList(it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun populateList(favoritesMovies: List<MovieDTO>) {
        val layoutManager = LinearLayoutManager(this)
        with(binding) {
            favoritesList.layoutManager = layoutManager
            favoritesList.adapter = FavoriteMoviesAdapter(favoritesMovies)
            favoritesList.adapter?.notifyDataSetChanged()

            favoritesList.visibility = VISIBLE
            loaderView.visibility = INVISIBLE
        }
    }
}