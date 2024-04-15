package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val favoriteMovies = mutableListOf<MovieFavoriteEntity>()
    private val searchResults = mutableListOf<MovieFavoriteEntity>()

    fun loadFavorites() {
        viewModelScope.launch {
            _uiState.value = FavoritesUiState.Loading
            favoriteMovies.clear()

            val favorites = repository.getFavoriteMovies()

            if (favorites.isSuccess && favorites.getOrNull()?.isNotEmpty() == true) {
                favoriteMovies.addAll(favorites.getOrNull().orEmpty())
                _uiState.value = FavoritesUiState.Success(favoriteMovies)
            } else {
                _uiState.value = FavoritesUiState.Error
            }
        }
    }

    fun searchFavorites(query: String) {
        viewModelScope.launch {
            searchResults.clear()

            if (query.isEmpty()) {
                _uiState.value = FavoritesUiState.Success(favoriteMovies)
                return@launch
            }

            val results = favoriteMovies.filter {
                it.title.contains(query, ignoreCase = true)
            }

            searchResults.addAll(results)
            _uiState.value = FavoritesUiState.Success(searchResults)
        }
    }
}