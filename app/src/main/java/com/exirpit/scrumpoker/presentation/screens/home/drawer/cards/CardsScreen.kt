package com.exirpit.scrumpoker.presentation.screens.home.drawer.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exirpit.scrumpoker.data.db.relations.CardDeckWithCards
import com.exirpit.scrumpoker.presentation.common.composable.SPTopAppBarDefault
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme

@Composable
fun CardsScreen(
    viewModel: CardsScreenViewModel = hiltViewModel(),
    onNavigateBackClicked: () -> Unit,
    onCardEditClicked: (CardDeckWithCards) -> Unit
) {
    ScrumPokerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                SPTopAppBarDefault(
                    title = "Cards", //TODO localize
                    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    onNavigationClick = {
                        onNavigateBackClicked.invoke()
                    }
                )

                val defaultCardsState by viewModel.defaultCardsStateFlow.collectAsState()

                Text(text = "Default Cards") //TODO localize
                LazyColumn {
                    items(defaultCardsState) {
                        Row {
                            RadioButton(
                                selected = it.cardDeck.isMainCardDeck,
                                onClick = {
                                    TODO()
                                }
                            )
                            Column {
                                Text(text = it.cardDeck.title)
                                Text(text = it.cardDeck.description)
                            }
                            IconButton(
                                onClick = {
                                    TODO()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Open edit card deck page icon" //TODO localize
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}