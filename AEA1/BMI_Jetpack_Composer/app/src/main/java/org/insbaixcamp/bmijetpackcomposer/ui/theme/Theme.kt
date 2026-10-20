package org.insbaixcamp.bmijetpackcomposer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DbYellowGold,
    onPrimary = DbBorderBlack,
    primaryContainer = DbOrangePrimary,
    onPrimaryContainer = DbSurfaceLight,
    secondary = DbBlueSecondary,
    onSecondary = DbSurfaceLight,
    background = DbBackgroundDark,
    onBackground = DbSurfaceLight,
    surface = DbSurfaceDark,
    onSurface = DbSurfaceLight,
    surfaceVariant = DbSurfaceDark,
    onSurfaceVariant = DbSurfaceLight
)

private val LightColorScheme = lightColorScheme(
    primary = DbOrangePrimary,
    onPrimary = DbSurfaceLight,
    primaryContainer = DbYellowGold,
    onPrimaryContainer = DbBorderBlack,
    secondary = DbBlueSecondary,
    onSecondary = DbSurfaceLight,
    secondaryContainer = DbBlueContainer,
    onSecondaryContainer = DbBorderBlack,
    background = DbBackgroundLight,
    onBackground = DbBorderBlack,
    surface = DbSurfaceLight,
    onSurface = DbBorderBlack,
    surfaceVariant = DbOrangeContainer,
    onSurfaceVariant = DbBorderBlack
)

@Composable
fun BMIJetpackComposerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
