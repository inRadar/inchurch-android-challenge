package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieResponse

sealed class MovieListUiState {
    data object Loading : MovieListUiState()

    data class Success(
        val data: List<MovieResponse>
    ) : MovieListUiState()

    data class ReachedLastPage(
        val data: List<MovieResponse>
    ) : MovieListUiState()

    data class Error(val message: String) : MovieListUiState()
}