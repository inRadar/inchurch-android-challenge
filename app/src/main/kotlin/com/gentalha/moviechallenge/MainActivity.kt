package com.gentalha.moviechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.gentalha.moviechallenge.ui.navigation.BottomNavigationItem
import com.gentalha.moviechallenge.ui.tab.FavoriteTab
import com.gentalha.moviechallenge.ui.tab.home.MoviesTab
import com.gentalha.moviechallenge.ui.theme.BlueDark
import com.gentalha.moviechallenge.ui.theme.BlueGrey
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
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val home = MoviesTab
    TabNavigator(tab = home) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .background(BlueDark)
                        .clip(
                            RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                        .height(60.dp),
                    containerColor = BlueGrey
                ) {
                    BottomNavigationItem(tab = home)
                    BottomNavigationItem(tab = FavoriteTab)
                }
            }
        ) {
            Box(
                Modifier.padding(it)
            ) {
                CurrentTab()
            }
        }
    }
}