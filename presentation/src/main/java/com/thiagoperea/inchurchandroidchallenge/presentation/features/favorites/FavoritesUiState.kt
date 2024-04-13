package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity

sealed class FavoritesUiState {
    data object Loading : FavoritesUiState()

    data class Success(val data: List<MovieFavoriteEntity>) : FavoritesUiState()

    data object Error : FavoritesUiState()
}