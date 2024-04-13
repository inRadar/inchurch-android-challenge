package com.thiagoperea.inchurchandroidchallenge.presentation.features.moviedetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme

@Composable
fun MoviesDetailsStrip(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_calendar),
            contentDescription = "Release Date",
            tint = AppColors.Gray
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "9999",
            style = AppTextStyle.Medium12,
            color = AppColors.Gray
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .height(16.dp)
                .width(1.dp)
                .background(AppColors.Gray)
        )

        Icon(
            painter = painterResource(R.drawable.ic_clock),
            contentDescription = "Movie Duration",
            tint = AppColors.Gray
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "999 Minutes",
            style = AppTextStyle.Medium12,
            color = AppColors.Gray
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .height(16.dp)
                .width(1.dp)
                .background(AppColors.Gray)
        )

        Icon(
            painter = painterResource(R.drawable.ic_ticket),
            contentDescription = "Genres",
            tint = AppColors.Gray
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Genre A, Genre B",
            style = AppTextStyle.Medium12,
            color = AppColors.Gray
        )
    }
}

@Preview
@Composable
private fun MoviesDetailsStripPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MoviesDetailsStrip()
        }
    }
}