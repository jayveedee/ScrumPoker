package com.exirpit.scrumpoker.presentation.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerExpandedCard
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerGridCard
import com.exirpit.scrumpoker.presentation.common.state.CardState
import com.exirpit.scrumpoker.presentation.home.viewModel.HomeScreenViewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    val itemList = mutableListOf<CardState>(
        CardState("1"),
        CardState("2"),
        CardState("3"),
        CardState("4"),
        CardState("5"),
        CardState("6"),
        CardState("7"),
        CardState("9"),
        CardState("10"),
        CardState("11")
    )
    ScrumPokerTheme {
        var expandedItem by remember { mutableStateOf(itemList[0])}
        var isExpanded by remember { mutableStateOf(false)}

        LazyVerticalGrid(columns = GridCells.Fixed(3), verticalArrangement = Arrangement.Center) {
            items(itemList) {
                ScrumPokerGridCard(card = it, onExpandedStateChanged = { ->
                    expandedItem = it
                    isExpanded = true
                })
            }
        }

        AnimatedVisibility(
            visible = isExpanded,
            modifier = Modifier.fillMaxSize()
        ) {
            ScrumPokerExpandedCard(card = expandedItem, onExpandedStateChanged = {
                isExpanded = false
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SinglePlayerScreenPreview() {
    ScrumPokerTheme {
        HomeScreen(
            viewModel = HomeScreenViewModel()
        )
    }
}