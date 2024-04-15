package com.example.movies.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
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

        binding = ActivityFavoriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpList()
        setUpObservers()

    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.favoritesToolBar)
        binding.closeButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
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
            favoritesList.adapter = FavoriteMoviesAdapter(favoritesMovies, this@FavoriteMoviesActivity)
            favoritesList.adapter?.notifyDataSetChanged()

            favoritesListLayout.visibility = VISIBLE
            loaderView.visibility = INVISIBLE
        }
    }

    fun startDetailsActivity(movie: MovieDTO) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}