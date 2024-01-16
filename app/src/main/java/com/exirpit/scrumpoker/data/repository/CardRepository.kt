package com.exirpit.scrumpoker.data.repository

import com.exirpit.scrumpoker.data.cache.dao.CardDAO
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import com.exirpit.scrumpoker.domain.repository.ICardRepository

class CardRepository(
    private val dao: CardDAO
) : ICardRepository {

    override fun getFibonacciCards(): List<Card> {
        return dao.getSpecificCards(CardType.Fibonacci) ?: emptyList()
    }

    override fun getPrimeCards(): List<Card> {
        return dao.getSpecificCards(CardType.Prime) ?: emptyList()
    }
}