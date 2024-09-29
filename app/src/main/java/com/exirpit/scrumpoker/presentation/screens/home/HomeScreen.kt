package com.exirpit.scrumpoker.presentation.screens.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import com.exirpit.scrumpoker.R
import com.exirpit.scrumpoker.presentation.common.composable.SPTopAppBarDefault
import com.exirpit.scrumpoker.data.db.entities.card.Card
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerExpandedCard
import com.exirpit.scrumpoker.presentation.common.composable.ScrumPokerGridCard
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigationItemClicked: (String) -> Unit
) {
    val itemList by viewModel.cardsStateFlow.collectAsState()

    ScrumPokerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                var expandedItem by remember { mutableStateOf(Card()) }
                var isExpanded by remember { mutableStateOf(false) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val drawerScope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            NavigationDrawerSheet(
                                onNavigationItemClicked = { it ->
                                    if (it == "Modern" || it == "Traditional") { // TODO use localized strings
                                        //TODO blah blah skifta kort ella prompt online lobby/connect/create stuff
                                    } else {
                                        onNavigationItemClicked.invoke(it)
                                    }
                                }
                            )
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column {
                                SPTopAppBarDefault(
                                    title = stringResource(id = R.string.app_bar_home_screen_title),
                                    navigationIcon = Icons.Default.Menu,
                                    onNavigationClick = {
                                        drawerScope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
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
                                ScrumPokerExpandedCard(
                                    card = expandedItem,
                                    onExpandedStateChanged = {
                                        isExpanded = false
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun NavigationDrawerSheet(
    onNavigationItemClicked: (String) -> Unit
) {
    ModalDrawerSheet {
        IconButton(
            modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                // Do nothing
            }
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "User image", //TODO localize
                modifier = Modifier.size(100.dp)
            )
        }
        Text(
            text = "Jákup Viljam Dam", //TODO fetch from viewmodel
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Engineer", //TODO fetch from viewmodel
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        HorizontalDivider()
        Text(
            text = "Estimation Session", //TODO localize
            modifier = Modifier.padding(16.dp)
        )
        CustomNavigationDrawerItem(
            label = "Traditional", //TODO localize
            isSelected = true,
            onClick = {
                onNavigationItemClicked.invoke("Traditional") //TODO use localized string
            }
        )
        CustomNavigationDrawerItem(
            label = "Modern", //TODO localize
            isSelected = false,
            onClick = {
                onNavigationItemClicked.invoke("Modern") //TODO use localized string
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))
        Text(
            text = "Profile", //TODO localize
            modifier = Modifier.padding(16.dp)
        )
        CustomNavigationDrawerItem(
            label = "User", //TODO localize
            isSelected = false,
            onClick = {
                onNavigationItemClicked.invoke("User") //TODO use localized string
            }
        )
        CustomNavigationDrawerItem(
            label = "Cards", //TODO localize
            isSelected = false,
            onClick = {
                onNavigationItemClicked.invoke("Cards") //TODO use localized string
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))
        Text(
            text = "Advanced", //TODO localize
            modifier = Modifier.padding(16.dp)
        )
        CustomNavigationDrawerItem(
            label = "Settings", //TODO localize
            isSelected = false,
            onClick = {
                onNavigationItemClicked.invoke("Settings") //TODO use localized string
            }
        )
        CustomNavigationDrawerItem(
            label = "About", //TODO localize
            isSelected = false,
            onClick = {
                onNavigationItemClicked.invoke("About") //TODO use localized string
            }
        )
    }
}

@Composable
private fun CustomNavigationDrawerItem(
    label: String,
    isSelected: Boolean,
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