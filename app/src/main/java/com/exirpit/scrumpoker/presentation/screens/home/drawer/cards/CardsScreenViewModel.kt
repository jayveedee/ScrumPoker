package com.exirpit.scrumpoker.presentation.screens.home.drawer.cards

import androidx.lifecycle.viewModelScope
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity
import com.exirpit.scrumpoker.data.db.relations.CardDeckWithCards
import com.exirpit.scrumpoker.domain.repository.ICardDeckRepository
import com.exirpit.scrumpoker.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsScreenViewModel @Inject constructor(
    private val cardDeckRepository: ICardDeckRepository
) : BaseViewModel()
{
    //--------------------------------------------------------------------------------------------//
    // Flows
    //--------------------------------------------------------------------------------------------//
    private val _defaultCardsStateFlow = MutableStateFlow<List<CardDeckWithCards>>(emptyList())
    val defaultCardsStateFlow = _defaultCardsStateFlow.asStateFlow()

    private val _customCardsStateFlow = MutableStateFlow<List<CardDeckWithCards>>(emptyList())
    val customCardsStateFlow = _customCardsStateFlow.asStateFlow()

    //--------------------------------------------------------------------------------------------//
    // Attributes
    //--------------------------------------------------------------------------------------------//

    //--------------------------------------------------------------------------------------------//
    // Initializer
    //--------------------------------------------------------------------------------------------//
    init {
        viewModelScope.launch {
            _defaultCardsStateFlow.update {
                cardDeckRepository.getDefaultCardDecks()
            }
            _customCardsStateFlow.update {
                cardDeckRepository.getCustomCardDecks()
            }
        }
    }
}