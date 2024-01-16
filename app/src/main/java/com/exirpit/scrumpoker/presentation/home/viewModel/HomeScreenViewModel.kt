package com.exirpit.scrumpoker.presentation.home.viewModel

import com.exirpit.scrumpoker.domain.repository.ICacheRepository
import com.exirpit.scrumpoker.presentation.BaseViewModel

class HomeScreenViewModel(
    private val cacheRepository: ICacheRepository
) : BaseViewModel() {

    val cards = cacheRepository.getHomeScreenCards()
}