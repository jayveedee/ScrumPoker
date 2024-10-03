package com.exirpit.scrumpoker.presentation.screens.home.drawer.cards

import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity
import com.exirpit.scrumpoker.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CardsScreenViewModel @Inject constructor(
) : BaseViewModel()
{
    //--------------------------------------------------------------------------------------------//
    // Flows
    //--------------------------------------------------------------------------------------------//
    private val _defaultCardsStateFlow = MutableStateFlow<List<CardDeckEntity>>(emptyList())

    //--------------------------------------------------------------------------------------------//
    // Attributes
    //--------------------------------------------------------------------------------------------//

    //--------------------------------------------------------------------------------------------//
    // Initializer
    //--------------------------------------------------------------------------------------------//
    init {

    }
}