package com.gentalha.moviechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.moviechallenge.data.mappers.toEntity
import com.gentalha.moviechallenge.data.mappers.toUi
import com.gentalha.moviechallenge.data.repository.FavoriteRepository
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.state.FavoriteUiState
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
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {
    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<FavoriteUiState>(
        FavoriteUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        getFavorites()
    }

    fun getFavorites() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            repository.getFavorites()
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { FavoriteUiState.Loading } }
                .map { exchanges ->
                    exchanges.map { it.toUi() }
                }
                .catch { error ->
                    _uiState.update { FavoriteUiState.Failure(error) }
                }
                .collect { exchanges ->
                    _uiState.update {
                        if (exchanges.isEmpty()) {
                            FavoriteUiState.Empty
                        } else {
                            FavoriteUiState.Success(exchanges)
                        }
                    }
                }
        }
    }

    fun updateFavorite(movie: Movie) {
        viewModelScope.launch {
            // TODO: criar UiState para favoritar para mostrar uma snack bar
            repository.addFavorite(movie.toEntity()).runCatching {
                println("THG_update -> runCatching state loading")
            }.onSuccess {
                println("THG_update -> onSuccess")
                getFavorites()
            }.onFailure {
                println("THG_update -> onFailure : ${it.message}")
            }
        }
    }
}