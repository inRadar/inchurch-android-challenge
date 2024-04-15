package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieResponse
import com.thiagoperea.inchurchandroidchallenge.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val movieList = mutableListOf<MovieResponse>()
    private var currentPage = 1
    private var totalPages = 1

    fun getMovieList() {
        viewModelScope.launch {
            _uiState.emit(MovieListUiState.Loading)

            val movieListResult = repository.getMovieList()

            if (movieListResult.isSuccess) {
                movieList.addAll(movieListResult.getOrNull()?.results.orEmpty())
                currentPage = movieListResult.getOrNull()?.page ?: 1
                totalPages = movieListResult.getOrNull()?.totalPages ?: 1

                _uiState.emit(MovieListUiState.Success(movieList))
            } else {
                _uiState.emit(MovieListUiState.Error(movieListResult.exceptionOrNull()?.message ?: "Unknown error"))
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            if (currentPage >= totalPages) {
                _uiState.emit(MovieListUiState.ReachedLastPage(movieList))
                return@launch
            }

            val movieListResult = repository.getMovieList(currentPage + 1)

            if (movieListResult.isSuccess) {
                movieList.addAll(movieListResult.getOrNull()?.results.orEmpty())
                currentPage = movieListResult.getOrNull()?.page ?: 1
                totalPages = movieListResult.getOrNull()?.totalPages ?: 1

                _uiState.emit(MovieListUiState.Success(movieList))
            } else {
                _uiState.emit(MovieListUiState.Error(movieListResult.exceptionOrNull()?.message ?: "Unknown error"))
            }
        }
    }
}