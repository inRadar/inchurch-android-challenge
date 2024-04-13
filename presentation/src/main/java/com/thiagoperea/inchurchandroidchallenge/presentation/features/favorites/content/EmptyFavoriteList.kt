package com.thiagoperea.inchurchandroidchallenge.presentation.features.favorites.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun EmptyFavoriteList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_empty_list),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.there_is_no_favorite_movie_yet),
            style = AppTextStyle.SemiBold16
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.find_a_movie_and_save_it),
            style = AppTextStyle.Medium12,
            color = AppColors.Gray
        )
    }
}

@Preview
@Composable
private fun PreviewEmptyFavoriteList() {
    InChurchAndroidChallengeTheme {
        Surface {
            EmptyFavoriteList()
        }
    }
}