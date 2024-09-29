package com.exirpit.scrumpoker.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckType
import com.exirpit.scrumpoker.data.db.entities.card.CardEntity
import com.exirpit.scrumpoker.data.db.relations.CardDeckWithCards
import kotlinx.coroutines.flow.Flow

@Dao
interface ICardDeckDAO {
    //--------------------------------------------------------------------------------------------//
    // Card decks
    //--------------------------------------------------------------------------------------------//
    @Transaction
    @Query("SELECT * FROM CardDeckEntity WHERE isMainCardDeck = 1")
    fun getMainCardDeck(): Flow<CardDeckWithCards>
    @Transaction
    @Query("SELECT * FROM CardDeckEntity")
    fun getAllCardDecks(): List<CardDeckWithCards>?
    @Transaction
    @Query("SELECT * FROM CardDeckEntity WHERE type = :type")
    fun getSpecificCardDeck(type: CardDeckType): List<CardDeckWithCards>?
    @Upsert
    suspend fun upsertCardDeck(cardDeck: CardDeckEntity)
    @Upsert
    suspend fun upsertCardDecks(cardDecks: List<CardDeckEntity>)
    @Delete
    suspend fun deleteCardDeck(cardDeck: CardDeckEntity)
    @Delete
    suspend fun deleteCardDecks(cardDeck: List<CardDeckEntity>)

    //--------------------------------------------------------------------------------------------//
    // Cards
    //--------------------------------------------------------------------------------------------//
    @Upsert
    suspend fun upsertCard(card: CardEntity)
    @Upsert
    suspend fun upsertCards(cards: List<CardEntity>)
    @Delete
    suspend fun deleteCard(card: CardEntity)
    @Delete
    suspend fun deleteCards(cards: List<CardEntity>)
}