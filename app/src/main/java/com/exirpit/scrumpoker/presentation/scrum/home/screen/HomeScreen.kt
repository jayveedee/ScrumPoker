package com.exirpit.scrumpoker.presentation.scrum.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exirpit.scrumpoker.R
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.presentation.BaseViewModel
import com.exirpit.scrumpoker.presentation.common.composable.SPTopAppBarDefault
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerExpandedCard
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerGridCard
import com.exirpit.scrumpoker.presentation.scrum.home.viewModel.HomeScreenViewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    val itemList by viewModel.cardsStateFlow.collectAsState()

    ScrumPokerTheme {
        var expandedItem by remember { mutableStateOf(Card())}
        var isExpanded by remember { mutableStateOf(false)}

        Surface(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    SPTopAppBarDefault(
                        title = stringResource(id = R.string.app_bar_home_screen_title),
                        onNavigationClick = {

                        },
                        onActionClick = {
                            TODO()
                        }
                    )

                    LazyVerticalGrid(columns = GridCells.Fixed(3), verticalArrangement = Arrangement.Center) {
                        items(itemList) {
                            ScrumPokerGridCard(card = it, onExpandedStateChanged = { ->
                                expandedItem = it
                                isExpanded = true
                            })
                        }
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
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ScrumPokerTheme {
        HomeScreen(
            HomeScreenViewModel(
                CardRepository(
                    BaseViewModel.db.cardDAO,
                    BaseViewModel.db.preferencesDAO
                )
            )
        )
    }
}