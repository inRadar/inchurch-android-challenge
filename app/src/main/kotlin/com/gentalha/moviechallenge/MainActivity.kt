package com.gentalha.moviechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.gentalha.moviechallenge.ui.tab.home.HomeScreen
import com.gentalha.moviechallenge.ui.theme.BlueDark
import com.gentalha.moviechallenge.ui.theme.MovieChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlueDark
                ) {
                    Navigator(screen = HomeScreen()) { navigator ->
                        SlideTransition(navigator = navigator)
                    }
                }
            }
        }
    }
}