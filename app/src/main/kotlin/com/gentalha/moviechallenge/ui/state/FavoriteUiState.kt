package com.gentalha.moviechallenge.ui.state

import com.gentalha.moviechallenge.ui.model.Movie

sealed class FavoriteUiState {

    data object Loading : FavoriteUiState()

    data object Empty : FavoriteUiState()

    data object SearchEmpty : FavoriteUiState()

    data class Success(
        val movies: List<Movie> = emptyList()
    ) : FavoriteUiState()

    data class Failure(
        val error: Throwable
    ) : FavoriteUiState()
}