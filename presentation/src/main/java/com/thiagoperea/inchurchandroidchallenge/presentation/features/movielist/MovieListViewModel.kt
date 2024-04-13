package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieListResponse
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getMovieList() {
        viewModelScope.launch {
            _uiState.emit(MovieListUiState.Loading)

            val movieListResult = repository.getMovieList()
            val movieList = movieListResult.getOrNull()

            if (movieListResult.isSuccess && movieList != null) {
                _uiState.emit(MovieListUiState.Success(movieList))
            } else {
                _uiState.emit(MovieListUiState.Error(movieListResult.exceptionOrNull()?.message ?: "Unknown error"))
            }
        }
    }
}