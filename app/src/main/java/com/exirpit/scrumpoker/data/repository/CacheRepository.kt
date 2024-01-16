package com.exirpit.scrumpoker.data.repository

import com.exirpit.scrumpoker.data.cache.dao.PreferencesDAO
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.repository.ICacheRepository

class CacheRepository(
    private val dao: PreferencesDAO
) : ICacheRepository {

    override fun getHomeScreenCards(): List<Card> {
        return dao.getPreferences()?.homeScreenCards ?: emptyList()
    }

    override suspend fun setHomeScreenCards(cards: List<Card>) {
        dao.getPreferences()?.copy(
            homeScreenCards = cards
        )?.let {
            dao.insertPreferences(
                it
            )
        }
    }
}