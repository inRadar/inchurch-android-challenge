package com.gentalha.moviechallenge.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.gentalha.moviechallenge.ui.theme.BlueLight
import com.gentalha.moviechallenge.ui.theme.TextLight

@Composable
fun FeedbackState(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
    title: String? = null,
    message: String? = null,
    actions: @Composable (ColumnScope.() -> Unit)? = null
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
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

                icon?.invoke()

                LargeSpacer()

                if (!title.isNullOrBlank()) Text(
                    text = title,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = TextLight,
                    textAlign = TextAlign.Center
                )

                MediumSpacer()

                if (!message.isNullOrBlank()) Text(
                    text = message,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Default,
                    color = BlueLight,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                MediumSpacer()

                actions?.invoke(this)
            }
        }
    }

}