package com.gentalha.moviechallenge.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gentalha.moviechallenge.R
import com.gentalha.moviechallenge.ui.components.FeedbackState
import com.gentalha.moviechallenge.ui.components.MediumSpacer
import com.gentalha.moviechallenge.ui.components.PrimaryButton
import com.gentalha.moviechallenge.ui.components.SmallSpacer
import com.gentalha.moviechallenge.ui.state.MovieDetailUiState
import com.gentalha.moviechallenge.ui.theme.BlueLight

@Composable
fun MovieDetail(uiState: MovieDetailUiState, retryOnClick: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
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
                    icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = null) },
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
                                model = uiState.movie.posterUrl,
                                contentDescription = uiState.movie.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            MediumSpacer()

                            Text(
                                text = uiState.movie.title,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            SmallSpacer()
                            Text(
                                text = uiState.movie.overview,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Default,
                                color = BlueLight,
                                maxLines = 6,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

            }
        }

    }

}