package com.gentalha.moviechallenge.ui.screens

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.gentalha.moviechallenge.ui.viewmodel.MovieViewModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailScreen(val movieId: Int) : Screen, Parcelable {
    @Composable
    override fun Content() {
        val viewModel: MovieViewModel = getViewModel()
        val uiState by viewModel.detailUiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.fetchMovieDetailBy(movieId)
        }

        MovieDetail(uiState = uiState) {
            viewModel.fetchMovieDetailBy(movieId)
        }
    }
}
