package com.exirpit.scrumpoker.presentation.singlePlayer.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerShapes
import com.exirpit.scrumpoker.presentation.singlePlayer.viewModel.SinglePlayerViewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTypography

@Composable
fun SinglePlayerScreen(
    viewModel: SinglePlayerViewModel = viewModel()
) {
    val itemList = mutableListOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
    ScrumPokerTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(3), verticalArrangement = Arrangement.Center) {
            items(itemList) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(120.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 5.dp
                    ),
                    shape = ScrumPokerShapes.large
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Title",
                                style = ScrumPokerTypography.headlineSmall,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SinglePlayerScreenPreview() {
    ScrumPokerTheme {
        SinglePlayerScreen(
            viewModel = SinglePlayerViewModel()
        )
    }
}