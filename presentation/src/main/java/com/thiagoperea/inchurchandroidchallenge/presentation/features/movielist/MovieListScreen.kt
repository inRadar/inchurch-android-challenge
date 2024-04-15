package com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.R
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
    val uiState = viewModel.uiState.collectAsState()

    val gridState = rememberLazyGridState()
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val buffer = 8
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == gridState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getMovieList()
    }

    LaunchedEffect(reachedBottom && uiState.value !is MovieListUiState.ReachedLastPage) {
        if (reachedBottom) {
            viewModel.loadMore()
        }
    }

    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        state = gridState,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(R.string.what_do_you_want_to_watch),
                style = AppTextStyle.SemiBold18,
            )
        }

        when (uiState.value) {
            is MovieListUiState.Loading -> movieListLoading()

            is MovieListUiState.Success -> movieListWithContent(
                data = (uiState.value as MovieListUiState.Success).data,
                appNavController = appNavController
            )

            is MovieListUiState.ReachedLastPage -> movieListWithContent(
                data = (uiState.value as MovieListUiState.ReachedLastPage).data,
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