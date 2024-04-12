package com.gentalha.moviechallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.theme.BlueLight

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    favoriteOnClick: (Movie) -> Unit
) {
    Row(modifier = modifier) {

        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(8.dp)
                )
                .height(190.dp)
        )

        SmallSpacer()
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = movie.overview,
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                color = BlueLight,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            SmallSpacer()

            FavoriteButton(
                hasFavorite = movie.isFavorite,
                modifier = Modifier.align(Alignment.End)
            ) {
                favoriteOnClick(movie.copy(isFavorite = it))
            }
        }
    }
}