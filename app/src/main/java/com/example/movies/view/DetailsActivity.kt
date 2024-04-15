package com.example.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movies.databinding.ActivityDetailsBinding
import com.example.movies.model.dtos.GenreDTO
import com.example.movies.model.dtos.MovieDTO
import com.example.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel: MoviesViewModel by viewModel()

    private val imageURL = "http://image.tmdb.org/t/p/w185"

    private var movie: MovieDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = intent.getParcelableExtra<MovieDTO>("movie")

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpScreen()
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.detailsToolBar)
        binding.closeButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setUpScreen() {

        var genres = ""

        movie?.genres?.forEach {
            genres += "${it.name}, "
        }


        with(binding) {
            Glide.with(backdropDetails.context)
                .load(imageURL + movie?.backdropPath)
                .into(backdropDetails)

            detailsTitle.text = movie?.title
            detailsGenres.text = genres
            detailsOverview.text = movie?.overview
        }
    }
}