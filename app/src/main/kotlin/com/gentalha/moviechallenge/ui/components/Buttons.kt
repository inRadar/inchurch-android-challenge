package com.gentalha.moviechallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gentalha.moviechallenge.ui.theme.BlueGrey
import com.gentalha.moviechallenge.ui.theme.BlueLight
import com.gentalha.moviechallenge.ui.theme.DarkGrey
import com.gentalha.moviechallenge.ui.theme.TextLight

@Composable
fun PrimaryButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = BlueGrey
        ), modifier = modifier
    ) {
        Text(text = label, color = TextLight)
    }
}

@Composable
fun SecondaryButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, DarkGrey),
        modifier = modifier
    ) {
        Text(text = label, color = BlueLight)
    }
}