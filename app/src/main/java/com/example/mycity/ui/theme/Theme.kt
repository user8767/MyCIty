package com.example.mycity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun MyCityTheme(content: @Composable () -> Unit) {
    val darkMode = isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (darkMode) darkColorScheme() else lightColorScheme(),
        typography = Typography,
        content = content
    )
}
