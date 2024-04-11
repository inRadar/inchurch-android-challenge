package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MovieDetailsScreen(
    appNavController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("MovieDetails Screen")
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsScreen(rememberNavController())
        }
    }
}