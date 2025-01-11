package com.exirpit.scrumpoker.presentation.screens.home.drawer.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exirpit.scrumpoker.data.db.relations.CardDeckWithCards
import com.exirpit.scrumpoker.presentation.common.composable.SPTopAppBarDefault
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CardsScreen(
    viewModel: CardsScreenViewModel = hiltViewModel(),
    onNavigateBackClicked: () -> Unit,
    onCardEditClicked: (CardDeckWithCards) -> Unit
) {
    ScrumPokerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                SPTopAppBarDefault(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = "Cards", //TODO localize
                    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    onNavigationClick = {
                        onNavigateBackClicked.invoke()
                    }
                )

                CardDeckSection(
                    text = "Default Cards", //TODO localize
                    cardDecksStateFlow = viewModel.defaultCardsStateFlow,
                    onRadioButtonClicked = {
                        TODO()
                    },
                    onEditButtonClicked = onCardEditClicked
                )

                val cardDecksState by viewModel.customCardsStateFlow.collectAsState()
                if (cardDecksState.isNotEmpty()) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))

                    CardDeckSection(
                        text = "Custom Cards", //TODO localize
                        cardDecksStateFlow = viewModel.customCardsStateFlow,
                        onRadioButtonClicked = {
                            TODO()
                        },
                        onEditButtonClicked = onCardEditClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun CardDeckSection(
    modifier: Modifier = Modifier,
    text: String,
    cardDecksStateFlow: StateFlow<List<CardDeckWithCards>>,
    onRadioButtonClicked: (CardDeckWithCards) -> Unit,
    onEditButtonClicked: (CardDeckWithCards) -> Unit
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 6.dp,
                end = 16.dp,
                bottom = 5.dp
            )
        )
        val cardDecksState by cardDecksStateFlow.collectAsState()
        LazyColumn {
            items(cardDecksState) {
                CardDeckSectionItem(it, onRadioButtonClicked, onEditButtonClicked)
            }
        }
    }
}

@Composable
private fun CardDeckSectionItem(
    item: CardDeckWithCards,
    onRadioButtonClicked: (CardDeckWithCards) -> Unit,
    onEditButtonClicked: (CardDeckWithCards) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val isSelected = item.cardDeck.isMainCardDeck
        RadioButton(
            modifier = Modifier.fillMaxHeight(),
            selected = isSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondary
            ),
            onClick = {
                onRadioButtonClicked.invoke(item)
            }
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    onRadioButtonClicked.invoke(item)
                }
        ) {
            CardDeckSectionText(
                text = item.cardDeck.title,
                isSelected = isSelected,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
            CardDeckSectionText(
                text = item.cardDeck.description,
                isSelected = isSelected,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }
        IconButton(
            modifier = Modifier.wrapContentSize(),
            onClick = {
                onEditButtonClicked.invoke(item)
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Open edit card deck page icon" //TODO localize
            )
        }
    }
}

@Composable
private fun CardDeckSectionText(text: String, isSelected: Boolean, fontSize: TextUnit) {
    Text(
        text = text,
        fontSize = fontSize,
        color = if (isSelected)
            MaterialTheme.colorScheme.secondary
        else
            MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}