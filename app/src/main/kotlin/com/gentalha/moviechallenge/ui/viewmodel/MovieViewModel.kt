package com.gentalha.moviechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.gentalha.moviechallenge.data.cache.entity.MovieEntity
import com.gentalha.moviechallenge.data.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    pager: Pager<Int, MovieEntity>
) : ViewModel() {

    val moviePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toUi() }
        }
        .cachedIn(viewModelScope)
}