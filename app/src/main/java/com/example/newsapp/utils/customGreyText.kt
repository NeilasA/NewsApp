package com.example.newsapp.utils

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun customGreyText(
    text: String
) {
    Text(
        text = text,
        color = Color.Gray,
        style = TextStyle(fontSize = 12.dp.toTextDp())
    )
}