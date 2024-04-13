package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopBar(
    appNavController: NavController,
    showFavoriteButton: Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Details",
                style = AppTextStyle.SemiBold16,
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Back"
            )
        },
        actions = {
            if (showFavoriteButton) {
                IconButton(
                    onClick = { appNavController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun MovieDetailsTopBarPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MovieDetailsTopBar(
                rememberNavController(),
                showFavoriteButton = true
            )
        }
    }
}