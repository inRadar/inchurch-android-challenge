package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailsState>(MovieDetailsState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            _uiState.emit(MovieDetailsState.Loading)

            delay(5000L)

            val movieDetailsResult = repository.getMovieDetails(movieId)
            val movieDetails = movieDetailsResult.getOrNull()

            if (movieDetailsResult.isSuccess && movieDetails != null) {
                _uiState.emit(MovieDetailsState.Success(movieDetails))
            } else {
                _uiState.emit(MovieDetailsState.Error(movieDetailsResult.exceptionOrNull()?.message ?: "Unknown error"))
            }
        }
    }
}
