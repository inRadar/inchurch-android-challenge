package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.thiagoperea.inchurchandroidchallenge.data.asImageUrl
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.navigation.AppRoutes
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme
import com.thiagoperea.inchurchandroidchallenge.presentation.utils.convertDate

@Composable
fun FavoriteList(
    favorites: List<MovieFavoriteEntity>,
    appNavController: NavController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Spacer(Modifier)
        }

        items(favorites) { favorite ->
            Row(
                modifier = Modifier
                    .testTag(favorite.title)
                    .padding(horizontal = 32.dp)
                    .clickable { appNavController.navigate(AppRoutes.MovieDetails.getRouteWithParam(favorite.movieId)) },
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(145.dp)
                        .height(217.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    model = favorite.posterPath.asImageUrl(),
                    contentDescription = null,
                    clipToBounds = true,
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(R.drawable.ic_broken_image),
                )

                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = favorite.title,
                        style = AppTextStyle.Regular16
                    )

                    Row(
                        modifier = Modifier.padding(top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            tint = AppColors.Orange
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = String.format("%.1f", favorite.voteAverage),
                            style = AppTextStyle.SemiBold12,
                            color = AppColors.Orange
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_ticket),
                            contentDescription = null,
                            tint = Color.White
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = favorite.genres.joinToString(),
                            style = AppTextStyle.SemiBold12,
                            color = Color.White
                        )
                    }


                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = null,
                            tint = Color.White
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = favorite.releaseDate.convertDate(
                                inputFormat = "yyyy-MM-dd",
                                outputFormat = "yyyy"
                            ),
                            style = AppTextStyle.SemiBold12,
                            color = Color.White
                        )
                    }


                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = null,
                            tint = Color.White
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = stringResource(R.string.movie_lenght, favorite.movieLenght),
                            style = AppTextStyle.SemiBold12,
                            color = Color.White
                        )
                    }
                }
            }
        }

        item {
            Spacer(Modifier)
        }
    }
}


@Preview
@Composable
private fun PreviewFavoriteList() {
    InChurchAndroidChallengeTheme {
        Surface {
            FavoriteList(
                favorites = listOf(
                    MovieFavoriteEntity(
                        movieId = 1,
                        posterPath = "/poster1.jpg",
                        title = "Movie 1",
                        releaseDate = "2021-01-01",
                        voteAverage = 7.5,
                        genres = listOf("Action", "Adventure"),
                        movieLenght = 120,
                    ),
                    MovieFavoriteEntity(
                        movieId = 2,
                        posterPath = "/poster2.jpg",
                        title = "Movie 2",
                        releaseDate = "2021-02-02",
                        voteAverage = 8.5,
                        genres = listOf("Comedy", "Drama"),
                        movieLenght = 130,
                    )
                ),
                appNavController = rememberNavController()
            )
        }
    }
}