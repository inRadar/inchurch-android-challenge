package com.example.movies.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.MoviesRepository
import com.example.movies.model.dtos.MovieDTO
import com.example.movies.model.dtos.MoviesDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _movies = MutableLiveData<MoviesDTO>()
    val movies: LiveData<MoviesDTO> = _movies

    private val _favoriteMovies = MutableLiveData<List<MovieDTO>>()
    val favoriteMovies: LiveData<List<MovieDTO>> = _favoriteMovies

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    val favoritesKey = "favorites"
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

    fun getFavoriteMovies(ids: Set<String>) {
        viewModelScope.launch {
            try {
                val result = moviesRepository.getMoviesById(ids)
                _favoriteMovies.postValue(result)
            } catch (throwable: Throwable) {
                _error.postValue(true)
            }
        }
    }

    fun upDateFavoriteList(id: String, sharedPref: SharedPreferences) {
        val favoritesSet: HashSet<String>? = sharedPref.getStringSet(favoritesKey, setOf())
            ?.let { HashSet(it) }

        val editor = sharedPref.edit()
        if (favoritesSet?.contains(id) == true) {
            favoritesSet.remove(id)
            editor.putStringSet(favoritesKey, favoritesSet)
            editor.apply()

        } else {
            favoritesSet?.add(id)
            editor.putStringSet(favoritesKey, favoritesSet)
            editor.apply()
        }
    }

    fun isMovieFavorite(id: String, preferences: SharedPreferences?): Boolean {
        val favoritesSet: HashSet<String>? = preferences?.getStringSet(favoritesKey, setOf())
            ?.let { HashSet(it) }

        return favoritesSet?.contains(id) ?: false
    }
}