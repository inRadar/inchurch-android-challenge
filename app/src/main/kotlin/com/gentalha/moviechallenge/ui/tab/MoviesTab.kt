package com.gentalha.moviechallenge.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gentalha.moviechallenge.R
import com.gentalha.moviechallenge.ui.screens.MoviesScreen
import com.gentalha.moviechallenge.ui.viewmodel.FavoriteViewModel
import com.gentalha.moviechallenge.ui.viewmodel.MovieViewModel

data class MoviesTab(
    val detailOnClick: (Int) -> Unit
) : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Rounded.PlayArrow)
            val label = stringResource(id = R.string.movies)
            return remember {
                TabOptions(
                    index = 0u,
                    title = label,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: MovieViewModel = getViewModel()
        val favoriteViewModel: FavoriteViewModel = getViewModel()
        val movies = viewModel.moviePagingFlow.collectAsLazyPagingItems()

        MoviesScreen(
            movies = movies,
            movieDetailOnClick = {
                detailOnClick(it)
            },
            favoriteOnClick = favoriteViewModel::updateFavorite
        )
    }
}