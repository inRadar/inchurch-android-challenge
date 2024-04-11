package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.content.movieListError
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.content.movieListLoading
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.content.movieListWithContent
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    appNavController: NavController,
    viewModel: MovieListViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getMovieList()
    }

    val uiState = viewModel.uiState.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 24.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "What do you want to watch?",
                style = AppTextStyle.SemiBold18,
            )
        }

        when (uiState.value) {
            is MovieListUiState.Loading -> movieListLoading()

            is MovieListUiState.Success -> movieListWithContent(
                data = (uiState.value as MovieListUiState.Success).data,
                appNavController = appNavController
            )

            is MovieListUiState.Error -> movieListError(
                onTryAgainClick = {
                    viewModel.getMovieList()
                }
            )
        }

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }

}

@Preview
@Composable
fun LoadMoviesScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieListScreen(rememberNavController())
        }
    }
}