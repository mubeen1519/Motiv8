package com.motiv8.quote.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColorScheme(
    primary = primaryColor,
    secondary = secondaryDarkColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor,
    onTertiary = Color(0xff393E46),
    background = primaryTextColor,
    onBackground = secondaryTextColor,

)

private val LightColorPalette = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryLightColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor,
    onTertiary = Color(0xFFFFFFF6),
    background = secondaryTextColor,
    onBackground = primaryTextColor

)

@Composable
fun QuotifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}