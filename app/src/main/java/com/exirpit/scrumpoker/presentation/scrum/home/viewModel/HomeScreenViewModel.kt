package com.exirpit.scrumpoker.presentation.scrum.home.viewModel

import com.exirpit.scrumpoker.domain.repository.ICardRepository
import com.exirpit.scrumpoker.presentation.BaseViewModel

class HomeScreenViewModel(
    private val cacheRepository: ICardRepository
) : BaseViewModel() {

    val cards = cacheRepository.getMainScreenCards()
}