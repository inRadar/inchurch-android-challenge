package com.example.movies.view

import android.os.Bundle
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
        viewModel.getMovies()

    }

    private fun setUpObservers() {
        viewModel.movies.observe(this) { updateMoviesList(it) }
    }

    private fun updateMoviesList(moviesDTO: MoviesDTO) {

        val gridLayoutManager = GridLayoutManager(this, 2)
        val context = this
        with(binding){
            movieList.layoutManager = gridLayoutManager
            movieList.adapter = MoviesAdapter(moviesDTO, context)
            movieList.adapter?.notifyDataSetChanged()
        }

    }
}