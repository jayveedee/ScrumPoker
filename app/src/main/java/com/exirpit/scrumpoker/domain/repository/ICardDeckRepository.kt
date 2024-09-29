package com.exirpit.scrumpoker.domain.repository

import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardEntity
import com.exirpit.scrumpoker.data.db.relations.CardDeckWithCards
import kotlinx.coroutines.flow.Flow

interface ICardDeckRepository {
    //--------------------------------------------------------------------------------------------//
    // Card decks
    //--------------------------------------------------------------------------------------------//
    suspend fun getMainCardDeck(): Flow<CardDeckWithCards>
    suspend fun getAllCardDecks(): List<CardDeckWithCards>
    suspend fun getDefaultCardDecks(): List<CardDeckWithCards>
    suspend fun getCustomCardDecks(): List<CardDeckWithCards>

    suspend fun upsertCardDeck(cardDeck: CardDeckEntity)
    suspend fun upsertCardDecks(cardDecks: List<CardDeckEntity>)
    suspend fun deleteCardDeck(cardDeck: CardDeckEntity)
    suspend fun deleteCardDecks(cardDecks: List<CardDeckEntity>)

    //--------------------------------------------------------------------------------------------//
    // Cards
    //--------------------------------------------------------------------------------------------//
    suspend fun upsertCard(card: CardEntity)
    suspend fun upsertCards(cards: List<CardEntity>)
    suspend fun deleteCard(card: CardEntity)
    suspend fun deleteCards(card: List<CardEntity>)
}