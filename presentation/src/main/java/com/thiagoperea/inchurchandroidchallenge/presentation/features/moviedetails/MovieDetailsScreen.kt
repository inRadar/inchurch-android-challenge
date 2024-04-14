package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component.MovieDetailsTopBar
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.content.MovieDetailsContent
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.content.MovieDetailsError
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.content.MovieDetailsLoading
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    appNavController: NavController,
    movieId: String,
    viewModel: MovieDetailsViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(movieId.toLong())
    }

    val state = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MovieDetailsTopBar(
                appNavController = appNavController,
                showFavoriteButton = state.value is MovieDetailsState.Success,
                isFavorite = (state.value as? MovieDetailsState.Success)?.isMovieFavorite == true,
                onFavoriteClick = { newState ->
                    viewModel.toggleFavorite(movieId.toLong(), newState)
                }
            )
        }
    ) { safePadding ->

        when (state.value) {
            is MovieDetailsState.Loading -> {
                MovieDetailsLoading(Modifier.padding(safePadding))
            }

            is MovieDetailsState.Success -> {
                MovieDetailsContent(
                    modifier = Modifier.padding(safePadding),
                    movieDetails = (state.value as MovieDetailsState.Success).data
                )
            }

            is MovieDetailsState.Error -> {
                MovieDetailsError(
                    onTryAgainClick = {
                        viewModel.getMovieDetails(movieId.toLong())
                    }
                )
            }
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