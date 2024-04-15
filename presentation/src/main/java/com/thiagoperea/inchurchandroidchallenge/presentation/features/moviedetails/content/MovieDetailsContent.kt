package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.data.asImageUrl
import com.thiagoperea.inchurchandroidchallenge.data.model.MovieDetailsUiModel
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.CastItem
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MovieDetailsHeader
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MoviesDetailsStrip
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.ReviewItem
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

object MovieDetailsTabs {
    const val ABOUT_MOVIE = 0
    const val REVIEWS = 1
    const val CAST = 2
}

@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetailsUiModel?
) {
    val selectedTabIndex = remember { mutableIntStateOf(MovieDetailsTabs.ABOUT_MOVIE) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        MovieDetailsHeader(
            backgroundUrl = movieDetails?.details?.backdropPath.asImageUrl(),
            posterUrl = movieDetails?.details?.posterPath.asImageUrl(),
            title = movieDetails?.details?.title.orEmpty(),
            voteAverage = movieDetails?.details?.voteAverage ?: 0.0,
            isLoading = false
        )

        MoviesDetailsStrip(
            modifier = Modifier.padding(start = 28.dp, top = 16.dp, end = 28.dp),
            releaseDate = movieDetails?.details?.releaseDate.orEmpty(),
            movieLength = movieDetails?.details?.runtime ?: 0,
            genres = movieDetails?.details?.genres?.map { it.name }.orEmpty(),
            isLoading = false
        )

        TabRow(
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp),
            selectedTabIndex = selectedTabIndex.intValue,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.intValue]),
                    height = 4.dp,
                    color = AppColors.SelectedTabColor
                )
            },
            divider = {}
        ) {

            Tab(
                text = {
                    Text(
                        text = stringResource(R.string.about_movie),
                        style = if (selectedTabIndex.intValue == MovieDetailsTabs.ABOUT_MOVIE) {
                            AppTextStyle.Medium14
                        } else {
                            AppTextStyle.Regular14
                        }
                    )
                },
                selected = selectedTabIndex.intValue == MovieDetailsTabs.ABOUT_MOVIE,
                onClick = { selectedTabIndex.intValue = MovieDetailsTabs.ABOUT_MOVIE }
            )

            Tab(
                text = {
                    Text(
                        text = stringResource(R.string.reviews),
                        style = if (selectedTabIndex.intValue == MovieDetailsTabs.REVIEWS) {
                            AppTextStyle.Medium14
                        } else {
                            AppTextStyle.Regular14
                        }
                    )
                },
                selected = selectedTabIndex.intValue == MovieDetailsTabs.REVIEWS,
                onClick = { selectedTabIndex.intValue = MovieDetailsTabs.REVIEWS }
            )

            Tab(
                text = {
                    Text(
                        text = stringResource(R.string.cast),
                        style = if (selectedTabIndex.intValue == MovieDetailsTabs.CAST) {
                            AppTextStyle.Medium14
                        } else {
                            AppTextStyle.Regular14
                        }
                    )
                },
                selected = selectedTabIndex.intValue == MovieDetailsTabs.CAST,
                onClick = { selectedTabIndex.intValue = MovieDetailsTabs.CAST }
            )
        }

        when (selectedTabIndex.intValue) {
            MovieDetailsTabs.ABOUT_MOVIE -> {
                Text(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    text = movieDetails?.details?.overview.orEmpty(),
                    style = AppTextStyle.Regular12,
                )
            }

            MovieDetailsTabs.REVIEWS -> {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(movieDetails?.reviews?.results.orEmpty()) { reviewItem ->
                        ReviewItem(reviewItem)
                    }
                }
            }

            MovieDetailsTabs.CAST -> {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(65.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(movieDetails?.cast?.cast.orEmpty()) { castItem ->
                        CastItem(castItem)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsContentPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsContent(
                movieDetails = MovieDetailsUiModel()
            )
        }
    }
}