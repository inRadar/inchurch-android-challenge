package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    showFavoriteButton: Boolean,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit
) {

    val favoriteButton = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.movie_details),
                style = AppTextStyle.SemiBold16,
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.testTag("backButton"),
                onClick = {
                    appNavController.popBackStack()
                },
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = "Back"
                    )
                }
            )
        },
        actions = {
            if (showFavoriteButton) {
                IconButton(
                    modifier = Modifier.testTag("favoriteButton"),
                    onClick = {
                        onFavoriteClick(!isFavorite)
                    }
                ) {
                    Icon(
                        imageVector = favoriteButton,
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
                showFavoriteButton = true,
                isFavorite = false,
                onFavoriteClick = { }
            )
        }
    }
}