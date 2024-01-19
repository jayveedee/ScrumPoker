package com.exirpit.scrumpoker.presentation.scrum.home.viewModel

import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import com.exirpit.scrumpoker.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val cardRepository: ICardRepository
) : BaseViewModel() {

    private val _cardsStateFlow = MutableStateFlow<List<Card>>(emptyList())
    val cardsStateFlow = _cardsStateFlow.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _cardsStateFlow.update {
                cardRepository.getMainScreenCards()
            }
        }
    }
}