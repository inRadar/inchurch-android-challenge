package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse

sealed class MovieListUiState {
    data object Loading : MovieListUiState()

    data class Success(val data: MovieListResponse) : MovieListUiState()

    data class Error(val message: String) : MovieListUiState()
}