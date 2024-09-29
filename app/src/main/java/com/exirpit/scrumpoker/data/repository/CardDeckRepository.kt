package com.exirpit.scrumpoker.data.repository

import com.exirpit.scrumpoker.data.db.dao.ICardDeckDAO
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckType
import com.exirpit.scrumpoker.data.db.entities.card.CardEntity
import com.exirpit.scrumpoker.data.db.relations.CardDeckWithCards
import com.exirpit.scrumpoker.domain.repository.ICardDeckRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardDeckRepository @Inject constructor(
    private val cardDeckDAO: ICardDeckDAO
) : ICardDeckRepository {

    //--------------------------------------------------------------------------------------------//
    // Overrides
    //--------------------------------------------------------------------------------------------//
    override suspend fun getMainCardDeck(): Flow<CardDeckWithCards> {
        return cardDeckDAO.getMainCardDeck()
    }

    override suspend fun getAllCardDecks(): List<CardDeckWithCards> {
        return cardDeckDAO.getAllCardDecks() ?: emptyList()
    }

    override suspend fun getDefaultCardDecks(): List<CardDeckWithCards> {
        return cardDeckDAO.getSpecificCardDeck(CardDeckType.Default) ?: emptyList()
    }

    override suspend fun getCustomCardDecks(): List<CardDeckWithCards> {
        return cardDeckDAO.getSpecificCardDeck(CardDeckType.Custom) ?: emptyList()
    }

    override suspend fun upsertCardDeck(cardDeck: CardDeckEntity) {
        cardDeckDAO.upsertCardDeck(cardDeck)
    }

    override suspend fun upsertCardDecks(cardDecks: List<CardDeckEntity>) {
        cardDeckDAO.upsertCardDecks(cardDecks)
    }

    override suspend fun deleteCardDeck(cardDeck: CardDeckEntity) {
        cardDeckDAO.deleteCardDeck(cardDeck)
    }

    override suspend fun deleteCardDecks(cardDecks: List<CardDeckEntity>) {
        cardDeckDAO.deleteCardDecks(cardDecks)
    }

    override suspend fun upsertCard(card: CardEntity) {
        cardDeckDAO.upsertCard(card)
    }

    override suspend fun upsertCards(cards: List<CardEntity>) {
        cardDeckDAO.upsertCards(cards)
    }

    override suspend fun deleteCard(card: CardEntity) {
        cardDeckDAO.deleteCard(card)
    }

    override suspend fun deleteCards(card: List<CardEntity>) {
        cardDeckDAO.deleteCards(card)
    }
}