package com.gentalha.moviechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gentalha.moviechallenge.R
import com.gentalha.moviechallenge.ui.components.FavoriteButton
import com.gentalha.moviechallenge.ui.components.FeedbackState
import com.gentalha.moviechallenge.ui.components.LargeSpacer
import com.gentalha.moviechallenge.ui.components.MediumSpacer
import com.gentalha.moviechallenge.ui.components.PrimaryButton
import com.gentalha.moviechallenge.ui.components.SmallSpacer
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.state.MovieDetailUiState
import com.gentalha.moviechallenge.ui.theme.BlueLight
import com.gentalha.moviechallenge.ui.theme.TextLight

@Composable
fun MovieDetail(
    uiState: MovieDetailUiState,
    retryOnClick: () -> Unit,
    favoriteOnClick: (Movie) -> Unit,
    backOnClick: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { backOnClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = TextLight
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                if (uiState is MovieDetailUiState.Success) {
                    uiState.movie.isFavorite
                    FavoriteButton(
                        hasFavorite = uiState.movie.isFavorite
                    ) {
                        favoriteOnClick(uiState.movie.copy(isFavorite = it))
                    }
                }


            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            when (uiState) {
                MovieDetailUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = BlueLight
                    )
                }

                is MovieDetailUiState.Failure -> {
                    FeedbackState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null
                            )
                        },
                        title = stringResource(id = R.string.ops),
                        message = stringResource(id = R.string.generic_error_message)
                    ) {
                        PrimaryButton(label = stringResource(id = R.string.retry)) {
                            retryOnClick()
                        }
                    }
                }

                is MovieDetailUiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Column(modifier = Modifier.fillMaxSize()) {
                                AsyncImage(
                                    model = uiState.movie.backdropUrl,
                                    contentDescription = uiState.movie.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )

                                MediumSpacer()

                                Text(
                                    text = uiState.movie.title,
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                SmallSpacer()
                                Text(
                                    text = uiState.movie.overview,
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Default,
                                    color = BlueLight,
                                )
                                LargeSpacer()
                            }
                        }
                    }

                }
            }

        }
    }


}