package com.gentalha.moviechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.gentalha.moviechallenge.ui.components.MediumSpacer
import com.gentalha.moviechallenge.ui.components.MovieItem
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.theme.BlueDark
import com.gentalha.moviechallenge.ui.theme.BlueGrey
import com.gentalha.moviechallenge.ui.theme.BlueLight

@Composable
fun MoviesScreen(
    movies: LazyPagingItems<Movie>,
    movieDetailOnClick: (Int) -> Unit,
    favoriteOnClick: (Movie) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueDark)
    ) {
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable { movieDetailOnClick(movie.id) }
                    ) {
                        favoriteOnClick(it)
                    }
                }
            }
            val loadState = movies.loadState.mediator
            item {
                if (loadState?.refresh == LoadState.Loading) {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = BlueLight)
                    }
                }

                if (loadState?.append == LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(color = BlueLight)
                    }
                }

                if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                    val isPaginatingError =
                        (loadState.append is LoadState.Error) || movies.itemCount > 1
                    val error = if (loadState.append is LoadState.Error)
                        (loadState.append as LoadState.Error).error
                    else
                        (loadState.refresh as LoadState.Error).error

                    val modifier = if (isPaginatingError) {
                        Modifier.padding(8.dp)
                    } else {
                        Modifier.fillParentMaxSize()
                    }
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (!isPaginatingError) {
                            Icon(
                                modifier = Modifier
                                    .size(64.dp),
                                imageVector = Icons.Rounded.Warning, contentDescription = null
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = error.message ?: error.toString(),
                            textAlign = TextAlign.Center,
                        )

                        Button(
                            onClick = {
                                movies.refresh()
                            },
                            content = {
                                Text(text = "Refresh")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BlueGrey,
                                contentColor = Color.White,
                            )
                        )
                    }
                }
            }
        }
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BlueDark),
//        contentAlignment = Alignment.Center
//    ) {
//        when (movies.loadState.refresh) {
//            is LoadState.Error -> {
//                println("THG_error -> ${(movies.loadState.refresh as LoadState.Error).error}")
//                Text(
//                    text = "ERROR: ${(movies.loadState.refresh as LoadState.Error).error}",
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//
//            LoadState.Loading -> {
//                println("THG_loading")
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center),
//                    color = BlueLight,
//                )
//            }
//
//            is LoadState.NotLoading -> {
//                LazyColumn(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    item { MediumSpacer() }
//                    items(movies.itemCount) { index ->
//                        movies[index]?.let { movie ->
//                            MovieItem(
//                                movie = movie,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 16.dp)
//                                    .clickable { println("THG_movie click = $movie") }
//                            ) { favoriteOnClick(it) }
//                        }
//                    }
//                    item {
//                        if (movies.loadState.append is LoadState.Loading) {
//                            CircularProgressIndicator(
//                                modifier = Modifier.align(Alignment.Center),
//                                color = BlueLight,
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
}

