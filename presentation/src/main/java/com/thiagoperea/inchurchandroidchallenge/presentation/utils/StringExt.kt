package com.thiagoperea.inchurchandroidchallenge.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertDate(
    inputFormat: String,
    outputFormat: String
) = try {
    val inputDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    val outputDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    val date = inputDateFormat.parse(this)
    outputDateFormat.format(date ?: Date())
} catch (e: Exception) {
    this
}