package com.thiagoperea.inchurchandroidchallenge.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.FavoritesScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.features.home.components.HomeMenu
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.MovieListScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.navigation.HomeRoutes
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun HomeScreen(
    appNavController: NavController
) {
    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            HomeMenu(homeNavController)
        }
    ) { safePadding ->
        Column(Modifier.padding(safePadding)) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = homeNavController,
                startDestination = HomeRoutes.MovieList.route,
            ) {
                composable(HomeRoutes.MovieList.route) { MovieListScreen(homeNavController) }
                composable(HomeRoutes.Favorites.route) { FavoritesScreen(homeNavController) }
            }

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(AppColors.Accent)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    InChurchAndroidChallengeTheme {
        Surface {
            HomeScreen(rememberNavController())
        }
    }
}