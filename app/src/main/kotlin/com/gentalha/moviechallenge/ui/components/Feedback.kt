package com.gentalha.moviechallenge.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.moviechallenge.ui.theme.BlueLight

private const val DEFAULT_RES = 0

@Composable
fun FeedbackState(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    @StringRes title: Int = DEFAULT_RES,
    @StringRes message: Int = DEFAULT_RES,
    @DrawableRes icon: Int = DEFAULT_RES,
) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = BlueLight
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "feedback icon",
                    modifier = Modifier.size(150.dp)
                )
                SmallSpacer()
                Text(
                    text = stringResource(id = title),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                SmallSpacer()
                Text(
                    text = stringResource(id = message),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default,
                    color = BlueLight,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}