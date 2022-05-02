package com.jonecx.qwit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,
    onPrimary = White,
    primaryVariant = Black,

    secondary = Blue,
    onSecondary = White,

    surface = Black,
    onSurface = White,

    background = Black,
    onBackground = White
)

private val LightColorPalette = lightColors(
    primary = ExtraExtraLightGray,
    onPrimary = Black,
    primaryVariant = ExtraExtraLightGray,

    secondary = Blue,
    onSecondary = White,

    surface = ExtraExtraLightGray,
    onSurface = Black,

    background = ExtraExtraLightGray,
    onBackground = Black
)

@Composable
fun QwitTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
