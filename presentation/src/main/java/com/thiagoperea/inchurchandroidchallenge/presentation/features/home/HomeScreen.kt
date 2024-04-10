package com.thiagoperea.inchurchandroidchallenge.presentation.features.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.AppScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.FavoritesScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.features.home.components.HomeMenu
import com.thiagoperea.inchurchandroidchallenge.presentation.features.movielist.MovieListScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.getAllAppScreens
import com.thiagoperea.inchurchandroidchallenge.presentation.ui.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.ui.theme.InChurchAndroidChallengeTheme

data class HomeMenuItem(
    val label: String,
    @DrawableRes val iconUnselected: Int,
    @DrawableRes val iconSelected: Int,
)

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            HomeMenu(navController)
        }
    ) { safePadding ->
        Column(Modifier.padding(safePadding)) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = AppScreen.MovieList.route,
            ) {
                composable(AppScreen.MovieList.route) { MovieListScreen(navController) }
                composable(AppScreen.Favorites.route) { FavoritesScreen(navController) }
            }

            Box(
                modifier = Modifier.
                height(1.dp)
                    .fillMaxWidth()
                    .background(AppColors.Accent)
            )
        }
    }
}

@Preview
@Composable
fun previewHomeScreen() {
    InChurchAndroidChallengeTheme {
        Surface {
            HomeScreen()
        }
    }
}