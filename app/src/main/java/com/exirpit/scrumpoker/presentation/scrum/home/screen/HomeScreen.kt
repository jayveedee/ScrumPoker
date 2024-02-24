package com.exirpit.scrumpoker.presentation.scrum.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exirpit.scrumpoker.R
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.presentation.BaseViewModel
import com.exirpit.scrumpoker.presentation.common.composable.SPTopAppBarDefault
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerExpandedCard
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerGridCard
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import com.exirpit.scrumpoker.presentation.scrum.home.viewModel.HomeScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel()
) {
    val itemList by viewModel.cardsStateFlow.collectAsState()

    ScrumPokerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val drawerScope = rememberCoroutineScope()

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    NavigationDrawerSheet()
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    var expandedItem by remember { mutableStateOf(Card()) }
                    var isExpanded by remember { mutableStateOf(false) }

                    Column {
                        SPTopAppBarDefault(
                            title = stringResource(id = R.string.app_bar_home_screen_title),
                            onNavigationClick = {
                                drawerScope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            onActionClick = {
                                TODO()
                            }
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            verticalArrangement = Arrangement.Center
                        ) {
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
}

@Composable
private fun NavigationDrawerSheet() {
    ModalDrawerSheet {
        Text(
            text = "Multiplayer", //TODO localize
            modifier = Modifier.padding(16.dp)
        )
        CustomNavigationDrawerItem(
            label = "Host", //TODO localize
            isSelected = false,
            onClick = {

            }
        )
        CustomNavigationDrawerItem(
            label = "Connect", //TODO localize
            isSelected = false,
            onClick = {

            }
        )
        Divider(modifier = Modifier.padding(horizontal = 12.dp))
        Text(
            text = "Cards", //TODO localize
            modifier = Modifier.padding(16.dp)
        )
        CustomNavigationDrawerItem(
            label = "Standard", //TODO localize
            isSelected = true,
            onClick = {

            }
        )
        CustomNavigationDrawerItem(
            label = "Custom", //TODO localize
            isSelected = false,
            onClick = {

            }
        )
        Divider(modifier = Modifier.padding(horizontal = 12.dp))
        Text(
            text = "Advanced", //TODO localize
            modifier = Modifier.padding(16.dp)
        )
        CustomNavigationDrawerItem(
            label = "Settings", //TODO localize
            isSelected = false,
            onClick = {

            }
        )
        CustomNavigationDrawerItem(
            label = "About", //TODO localize
            isSelected = false,
            onClick = {

            }
        )
        CustomNavigationDrawerItem(
            label = "Help", //TODO localize
            isSelected = false,
            onClick = {

            }
        )
    }
}

@Composable
private fun CustomNavigationDrawerItem(
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(horizontal = 12.dp),
        label = { Text(text = label) },
        selected = isSelected,
        onClick = { onClick.invoke() },
        icon = { CustomNavigationDrawerIcon() }
    )
}

@Composable
private fun CustomNavigationDrawerIcon() {
    Icon(
        modifier = Modifier.size(12.dp),
        painter = painterResource(id = R.drawable.ic_circle),
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = "Navigation item" //TODO localize
    )
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