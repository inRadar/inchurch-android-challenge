package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MovieDetailsHeader
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MovieDetailsTopBar
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MoviesDetailsStrip
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieDetailsScreen(
    appNavController: NavController,
    movieId: String
) {
    Scaffold(
        topBar = { MovieDetailsTopBar(appNavController) }
    ) { safePadding ->

        Column(
            modifier = Modifier
                .padding(safePadding)
                .fillMaxSize()
        ) {

            MovieDetailsHeader(
                backgroundUrl = "",
                posterUrl = "",
                title = "Movie Title",
                voteAverage = 8.553,
            )

            MoviesDetailsStrip(
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsScreen(rememberNavController(), "")
        }
    }
}