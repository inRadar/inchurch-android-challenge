package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailsState>(MovieDetailsState.Loading)
    val uiState = _uiState.asStateFlow()

    private var movieDetails: MovieDetailsUiModel? = null

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            _uiState.emit(MovieDetailsState.Loading)

            val isMovieFavorite = repository.isMovieFavorite(movieId)
            val movieDetailsResult = repository.getMovieDetails(movieId)
            movieDetails = movieDetailsResult.getOrNull()

            if (movieDetailsResult.isSuccess && movieDetails != null) {
                _uiState.emit(MovieDetailsState.Success(movieDetails, isMovieFavorite))
            } else {
                _uiState.emit(MovieDetailsState.Error(movieDetailsResult.exceptionOrNull()?.message ?: "Unknown error"))
            }
        }
    }

    fun toggleFavorite(movieId: Long, newState: Boolean) {
        viewModelScope.launch {
            if (newState) {
                repository.saveFavorite(movieDetails)
            } else {
                repository.removeFavorite(movieId)
            }

            val isMovieFavorite = repository.isMovieFavorite(movieId)
            _uiState.emit(MovieDetailsState.Success(movieDetails, isMovieFavorite))
        }
    }
}
