package com.example.rustore

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val RuBlue = Color(0xFF2D5AE3)

private val light = lightColorScheme(
    primary = RuBlue,
    onPrimary = Color.White,
    background = Color(0xFFF7F9FC),
    surface = Color.White
)

@Composable
fun RuStoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = light, content = content)
}
