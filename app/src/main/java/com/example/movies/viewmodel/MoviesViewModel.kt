package com.example.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.MoviesRepository
import com.example.movies.model.dtos.MoviesDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _movies = MutableLiveData<MoviesDTO>()
    val movies: LiveData<MoviesDTO> = _movies

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    fun getMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = moviesRepository.getPopularMovies()
                    _movies.postValue(result)
                } catch (throwable: Throwable) {
                    _error.postValue(true)
                }
            }
        }
    }
}