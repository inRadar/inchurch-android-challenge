package com.gentalha.moviechallenge.ui.state

import com.gentalha.moviechallenge.ui.model.Movie

sealed class MovieDetailUiState {
    data object Loading : MovieDetailUiState()

    data class Success(
        val movie: Movie
    ) : MovieDetailUiState()

    data class Failure(
        val error: Throwable
    ) : MovieDetailUiState()
}