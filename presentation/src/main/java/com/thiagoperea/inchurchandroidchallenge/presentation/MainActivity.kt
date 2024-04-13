package com.thiagoperea.inchurchandroidchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thiagoperea.inchurchandroidchallenge.presentation.features.home.HomeScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.MovieDetailsScreen
import com.thiagoperea.inchurchandroidchallenge.presentation.navigation.AppRoutes
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            InChurchAndroidChallengeTheme {
                val appNavController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(
                        navController = appNavController,
                        startDestination = AppRoutes.Home.route,
                    ) {
                        composable(AppRoutes.Home.route) {
                            HomeScreen(appNavController)
                        }

                        composable(
                            route = AppRoutes.MovieDetails.route,
                            arguments = listOf(
                                navArgument("movieId") { type = NavType.StringType }
                            ),
                        ) { backstackEntry ->
                            val movieId = backstackEntry.arguments?.getString("movieId").orEmpty()

                            MovieDetailsScreen(appNavController, movieId)
                        }
                    }
                }
            }
        }
    }
}