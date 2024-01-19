package com.exirpit.scrumpoker.data.repository

import com.exirpit.scrumpoker.data.dao.CardDAO
import com.exirpit.scrumpoker.data.dao.PreferencesDAO
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import kotlinx.coroutines.flow.StateFlow

class CardRepository(
    private val cardDAO: CardDAO,
    private val preferencesDAO: PreferencesDAO
) : ICardRepository {

    override suspend fun getMainScreenCards(): List<Card> {
        val preferences = preferencesDAO.getPreferences() ?: return getFibonacciCards()

        return if (preferences.preferredCards == CardType.Fibonacci)
            getFibonacciCards()
        else
            getPrimeCards()
    }

    override suspend fun getFibonacciCards(): List<Card> {
        return cardDAO.getSpecificCards(CardType.Fibonacci) ?: emptyList()
    }

    override suspend fun getPrimeCards(): List<Card> {
        return cardDAO.getSpecificCards(CardType.Prime) ?: emptyList()
    }
}