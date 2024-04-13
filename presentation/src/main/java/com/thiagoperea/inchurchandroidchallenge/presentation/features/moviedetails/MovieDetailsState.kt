package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel

sealed class MovieDetailsState {
    data object Loading : MovieDetailsState()

    data class Success(
        val data: MovieDetailsUiModel?,
        val isMovieFavorite: Boolean
    ) : MovieDetailsState()

    data class Error(val message: String) : MovieDetailsState()
}