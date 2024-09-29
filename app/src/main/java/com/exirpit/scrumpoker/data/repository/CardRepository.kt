package com.exirpit.scrumpoker.data.repository

import com.exirpit.scrumpoker.data.db.dao.CardDAO
import com.exirpit.scrumpoker.data.db.entities.card.Card
import com.exirpit.scrumpoker.data.db.entities.card.CardType
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardDAO: CardDAO,
) : ICardRepository {

    //--------------------------------------------------------------------------------------------//
    // Overrides
    //--------------------------------------------------------------------------------------------//
    override suspend fun getMainScreenCards(): List<Card> {
        TODO()
    }

    override suspend fun getFibonacciCards(): List<Card> {
        return listOf(
            Card("1", CardType.Fibonacci),
            Card("2", CardType.Fibonacci),
            Card("3", CardType.Fibonacci),
            Card("5", CardType.Fibonacci),
            Card("8", CardType.Fibonacci),
            Card("13", CardType.Fibonacci),
            Card("21", CardType.Fibonacci),
            Card("34", CardType.Fibonacci),
            Card("55", CardType.Fibonacci),
            Card("89", CardType.Fibonacci),
            Card("?", CardType.Fibonacci),
            Card("...", CardType.Fibonacci),
        )
    }

    override suspend fun getStandardCards(): List<Card> {
        return listOf(
            Card("1/2", CardType.Standard),
            Card("1", CardType.Standard),
            Card("2", CardType.Standard),
            Card("3", CardType.Standard),
            Card("5", CardType.Standard),
            Card("8", CardType.Standard),
            Card("13", CardType.Standard),
            Card("20", CardType.Standard),
            Card("40", CardType.Standard),
            Card("100", CardType.Standard),
            Card("?", CardType.Standard),
            Card("...", CardType.Standard),
        )
    }
}