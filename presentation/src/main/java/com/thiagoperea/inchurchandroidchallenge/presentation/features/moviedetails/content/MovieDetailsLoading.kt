package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MovieDetailsHeader
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MoviesDetailsStrip
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieDetailsLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        MovieDetailsHeader(
            backgroundUrl = "",
            posterUrl = "",
            title = "",
            voteAverage = 0.0,
            isLoading = true
        )

        MoviesDetailsStrip(
            modifier = Modifier.padding(start = 28.dp, top = 16.dp, end = 28.dp),
            releaseDate = "",
            movieLength = 0,
            genres = listOf(),
            isLoading = true
        )

        TabRow(
            modifier = Modifier.padding(top = 24.dp),
            selectedTabIndex = 0,
            indicator = {}
        ) {
            Tab(
                modifier = Modifier.background(shimmerBrush(true)),
                selected = true,
                onClick = {}
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 24.dp)
                .background(shimmerBrush(true))

        )
    }
}

@Preview
@Composable
fun MovieDetailsLoadingPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsLoading()
        }
    }
}