package com.exirpit.scrumpoker.presentation.common.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF000000)
val OxfordBlue = Color(0xFF1B2A41)
val LavenderWeb = Color(0xFFCCC9DC)

val ScrumPokerLightColors = lightColorScheme(
    primary = LavenderWeb,
    background = Black,
    onBackground = OxfordBlue
)

val ScrumPokerDarkColors = darkColorScheme(
    primary = LavenderWeb,
    background = Black,
    onBackground = OxfordBlue
)

