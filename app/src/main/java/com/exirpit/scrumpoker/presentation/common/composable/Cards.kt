package com.exirpit.scrumpoker.presentation.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScrumPokerCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 5.dp
        ),
        shape = MaterialTheme.shapes.large
    ) {

    }
}