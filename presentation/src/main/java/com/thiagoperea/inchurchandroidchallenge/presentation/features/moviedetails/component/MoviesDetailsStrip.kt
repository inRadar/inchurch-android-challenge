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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thiagoperea.inchurchandroidchallenge.presentation.R
import com.thiagoperea.inchurchandroidchallenge.presentation.components.shimmerBrush
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppColors
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.AppTextStyle
import com.thiagoperea.inchurchandroidchallenge.presentation.theme.InChurchAndroidChallengeTheme
import com.thiagoperea.inchurchandroidchallenge.presentation.utils.convertDate

@Composable
fun MoviesDetailsStrip(
    modifier: Modifier = Modifier,
    releaseDate: String,
    movieLength: Int,
    genres: List<String>,
    isLoading: Boolean
) {

    val elementColor = if (isLoading) {
        Color.Transparent
    } else {
        AppColors.Gray
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(shimmerBrush(isLoading)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_calendar),
            contentDescription = "Release Date",
            tint = elementColor
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = releaseDate.convertDate(
                inputFormat = "yyyy-MM-dd",
                outputFormat = "yyyy"
            ),
            style = AppTextStyle.Medium12,
            color = elementColor
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .height(16.dp)
                .width(1.dp)
                .background(elementColor)
        )

        Icon(
            painter = painterResource(R.drawable.ic_clock),
            contentDescription = "Movie Duration",
            tint = elementColor
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = stringResource(R.string.movie_lenght, movieLength),
            style = AppTextStyle.Medium12,
            color = elementColor
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .height(16.dp)
                .width(1.dp)
                .background(elementColor)
        )

        Icon(
            painter = painterResource(R.drawable.ic_ticket),
            contentDescription = "Genres",
            tint = elementColor
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = genres.take(2).joinToString(),
            style = AppTextStyle.Medium12,
            color = elementColor
        )
    }
}

@Preview
@Composable
private fun MoviesDetailsStripPreview() {
    InChurchAndroidChallengeTheme {
        Surface {
            MoviesDetailsStrip(
                releaseDate = "1990",
                movieLength = 240,
                genres = listOf("genre A", "genre B"),
                isLoading = false
            )
        }
    }
}