package com.exirpit.scrumpoker.domain.repository

import com.exirpit.scrumpoker.domain.model.card.Card

interface ICacheRepository {
    fun getHomeScreenCards(): List<Card>
    suspend fun setHomeScreenCards(cards: List<Card>)
}