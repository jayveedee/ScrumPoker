package com.exirpit.scrumpoker.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun ScrumPokerTheme(
    colors: ColorScheme = if (isSystemInDarkTheme()) ScrumPokerDarkColors else ScrumPokerLightColors,
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