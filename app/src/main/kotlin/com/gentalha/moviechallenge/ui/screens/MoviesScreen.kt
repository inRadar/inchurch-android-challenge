package com.gentalha.moviechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.gentalha.moviechallenge.ui.components.MediumSpacer
import com.gentalha.moviechallenge.ui.components.MovieItem
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.theme.BlueDark

@Composable
fun MoviesScreen(movies: LazyPagingItems<Movie>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlueDark)) {
        when (movies.loadState.refresh) {
            is LoadState.Error -> {
                println("THG_error -> ${(movies.loadState.refresh as LoadState.Error).error}")
                Text(
                    text = "ERROR: ${(movies.loadState.refresh as LoadState.Error).error}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
                )
            }

            LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is LoadState.NotLoading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { MediumSpacer() }
                    items(movies.itemCount) { index ->
                        movies[index]?.let { movie ->
                            MovieItem(
                                movie = movie,
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            ) {}
                        }
                    }
                    item {
                        if (movies.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}