package com.exirpit.scrumpoker.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ScrumPokerDarkColorScheme = darkColorScheme(
    primary = LavenderGray80,
    onPrimary = LavenderGray20,
    primaryContainer = LavenderGray30,
    onPrimaryContainer = LavenderGray90,
    inversePrimary = LavenderGray40,
    secondary = LavenderBlue90,
    onSecondary = LavenderBlue20,
    tertiary = Onyx20,
    onTertiary = Onyx80,
    tertiaryContainer = Onyx30,
    onTertiaryContainer = Onyx90,
    secondaryContainer = LavenderBlue30,
    onSecondaryContainer = LavenderBlue80,
    background = EerieBlack10,
    onBackground = EerieBlack40,
    surface = EerieBlack10,
    onSurface = EerieBlack40
)

private val ScrumPokerLightColorScheme = lightColorScheme(

)

@Composable
fun ScrumPokerTheme(
    colors: ColorScheme = if (isSystemInDarkTheme()) ScrumPokerDarkColorScheme else ScrumPokerLightColorScheme,
    typography: Typography = ScrumPokerTypography,
    shapes: Shapes = ScrumPokerShapes,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}