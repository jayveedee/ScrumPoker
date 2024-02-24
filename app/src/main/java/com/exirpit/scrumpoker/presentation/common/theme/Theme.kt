package com.exirpit.scrumpoker.presentation.common.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val ScrumPokerDarkColorScheme = darkColorScheme(
    primary = LavenderGray80,
    onPrimary = LavenderGray20,
    primaryContainer = LavenderGray30,
    onPrimaryContainer = LavenderGray90,
    inversePrimary = LavenderGray40,
    secondary = LavenderBlue90,
    onSecondary = LavenderGray10,
    tertiary = Onyx20,
    onTertiary = Onyx80,
    tertiaryContainer = Onyx30,
    onTertiaryContainer = Onyx90,
    secondaryContainer = EerieBlack30,
    onSecondaryContainer = LavenderBlue90,
    background = EerieBlack10,
    onBackground = EerieBlack40,
    surface = EerieBlack10,
    onSurface = EerieBlack40
)

private val ScrumPokerLightColorScheme = lightColorScheme(
    primary = White20,
    onPrimary = White80,
    primaryContainer = White30,
    onPrimaryContainer = White90,
    inversePrimary = PhilippineBronze40,
    secondary = MalachiteGreen80,
    onSecondary = MalachiteGreen20,
    tertiary = Onyx80,
    onTertiary = Onyx20,
    tertiaryContainer = Onyx90,
    onTertiaryContainer = Onyx30,
    secondaryContainer = White80,
    onSecondaryContainer = White10,
    background = EerieBlack40,
    onBackground = EerieBlack10,
    surface = White90,
    onSurface = White10
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