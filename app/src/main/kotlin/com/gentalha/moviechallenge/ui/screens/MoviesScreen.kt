package com.gentalha.moviechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.gentalha.moviechallenge.R
import com.gentalha.moviechallenge.ui.components.CircularLoading
import com.gentalha.moviechallenge.ui.components.FeedbackState
import com.gentalha.moviechallenge.ui.components.MediumSpacer
import com.gentalha.moviechallenge.ui.components.MovieItem
import com.gentalha.moviechallenge.ui.components.SecondaryButton
import com.gentalha.moviechallenge.ui.components.SmallSpacer
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.theme.BlueDark
import com.gentalha.moviechallenge.ui.theme.TextLight

@Composable
fun MoviesScreen(
    movies: LazyPagingItems<Movie>,
    movieDetailOnClick: (Int) -> Unit,
    favoriteOnClick: (Movie) -> Unit,
    retryOnClick: () -> Unit
) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .background(BlueDark)
                .padding(padding)
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
                MediumSpacer()
            }
            movies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularLoading(Modifier.align(Alignment.Center))
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                FeedbackState(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Warning,
                                            contentDescription = "error",
                                            tint = Color.White,
                                            modifier = Modifier.size(130.dp)
                                        )
                                    },
                                    title = stringResource(id = R.string.ops),
                                    message = stringResource(id = R.string.generic_error_message)
                                ) {
                                    SecondaryButton(label = stringResource(id = R.string.retry)) {
                                        retryOnClick()
                                    }
                                }
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularLoading(Modifier.align(Alignment.Center))
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    imageVector = Icons.Outlined.Warning,
                                    contentDescription = "error",
                                    tint = Color.Yellow
                                )

                                SmallSpacer()

                                Text(
                                    text = stringResource(id = R.string.generic_error_message),
                                    color = TextLight,
                                    fontSize = 14.sp
                                )

                                SmallSpacer()

                                SecondaryButton(label = stringResource(id = R.string.retry)) {
                                    retryOnClick()
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

