package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.content.EmptyFavoriteList
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.content.FavoriteList
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    appNavController: NavController,
    viewModel: FavoritesViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_favorites),
                    style = AppTextStyle.SemiBold16,
                )
            }
        )

        if (uiState.value is FavoritesUiState.Success) {
            FavoriteList(
                favorites = (uiState.value as FavoritesUiState.Success).data,
                appNavController = appNavController
            )
        } else {
            EmptyFavoriteList()
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            FavoritesScreen(rememberNavController())
        }
    }
}