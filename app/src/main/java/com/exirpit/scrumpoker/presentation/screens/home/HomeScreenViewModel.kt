package com.exirpit.scrumpoker.presentation.screens.home

import androidx.lifecycle.viewModelScope
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
class HomeScreenViewModel @Inject constructor (
    private val cardDeckRepository: ICardDeckRepository
) : BaseViewModel() {

    //--------------------------------------------------------------------------------------------//
    // Flows
    //--------------------------------------------------------------------------------------------//
    private val _cardsStateFlow = MutableStateFlow<CardDeckWithCards?>(null)
    val cardsStateFlow = _cardsStateFlow.asStateFlow()

    //--------------------------------------------------------------------------------------------//
    // Initializer
    //--------------------------------------------------------------------------------------------//
    init {
        viewModelScope.launch {
            //TODO add some loading state for UI
            cardDeckRepository.getMainCardDeck().collect { cardDeck ->
                _cardsStateFlow.update {
                    cardDeck
                }
            }
        }
    }
}