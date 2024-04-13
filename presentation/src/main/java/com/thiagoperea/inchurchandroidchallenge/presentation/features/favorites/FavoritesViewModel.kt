package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadFavorites() {
        viewModelScope.launch {
            _uiState.value = FavoritesUiState.Loading

            val favorites = repository.getFavoriteMovies()

            if (favorites.isSuccess && favorites.getOrNull()?.isNotEmpty() == true) {
                _uiState.value = FavoritesUiState.Success(favorites.getOrNull().orEmpty())
            } else {
                _uiState.value = FavoritesUiState.Error
            }
        }
    }
}