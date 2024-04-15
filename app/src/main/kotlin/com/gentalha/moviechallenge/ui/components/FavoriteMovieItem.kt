package com.gentalha.moviechallenge.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.theme.BlueGrey

@Composable
fun FavoriteMovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    movieOnclick: (Movie) -> Unit,
    favoriteOnClick: (Movie) -> Unit
) {
    Card(
        modifier = modifier.clickable { movieOnclick(movie) },
        colors = CardDefaults.cardColors(
            containerColor = BlueGrey
        )
    ) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title
        )

        SmallSpacer()

        Text(
            text = movie.title,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )

        FavoriteButton(
            hasFavorite = movie.isFavorite,
            modifier = Modifier.align(Alignment.End)
        ) {
            favoriteOnClick(movie.copy(isFavorite = it))
        }

    }
}