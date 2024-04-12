package com.gentalha.moviechallenge.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gentalha.moviechallenge.R
import com.gentalha.moviechallenge.ui.screens.FavoriteScreen
import com.gentalha.moviechallenge.ui.viewmodel.FavoriteViewModel

object FavoriteTab : Tab {
    private fun readResolve(): Any = FavoriteTab
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Favorite)
            val label = stringResource(id = R.string.favorite)
            return remember {
                TabOptions(
                    index = 1u,
                    title = label,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: FavoriteViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getFavorites()
        }
        FavoriteScreen(uiState, viewModel::updateFavorite)
    }
}