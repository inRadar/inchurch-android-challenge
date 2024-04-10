package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.thiagoperea.inchurchandroidchallenge.presentation.ui.theme.InChurchAndroidChallengeTheme

@Composable
fun FavoritesScreen(
    navController: NavController? = null
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Favorites Screen")
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            FavoritesScreen()
        }
    }
}