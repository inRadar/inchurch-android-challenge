package com.gentalha.moviechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gentalha.moviechallenge.data.mappers.toUi
import com.gentalha.moviechallenge.data.repository.MovieRepository
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.state.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState

    private var currentUiStateJob: Job? = null
    private val _detailUiState = MutableStateFlow<MovieDetailUiState>(
        MovieDetailUiState.Loading
    )
    val detailUiState = _detailUiState.asStateFlow()

    fun getMovies() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            movieRepository.getMovies()
                .flowOn(Dispatchers.IO)
                .map { pagingData ->
                    pagingData.map { it.toUi() }
                }
                .cachedIn(viewModelScope).collect { pagingMovie ->
                    _moviesState.update {
                        pagingMovie
                    }
                }
        }
    }

    fun fetchMovieDetailBy(id: Int) {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            movieRepository.getMovieDetail(id)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _detailUiState.update { MovieDetailUiState.Loading }
                }
                .catch { error ->
                    _detailUiState.update { MovieDetailUiState.Failure(error) }
                }
                .collect { movie ->
                    _detailUiState.update {
                        MovieDetailUiState.Success(movie.toUi())
                    }

                }
        }
    }
}