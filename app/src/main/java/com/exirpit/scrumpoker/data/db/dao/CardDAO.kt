package com.exirpit.scrumpoker.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.exirpit.scrumpoker.data.db.entities.card.Card
import com.exirpit.scrumpoker.data.db.entities.card.CardType

@Dao
interface CardDAO {
    @Query("SELECT * FROM card")
    fun getAllCards(): List<Card>?
    @Query("SELECT * FROM card WHERE type = :type")
    fun getSpecificCards(type: CardType): List<Card>?
    @Upsert
    suspend fun upsertCards(cards: List<Card>)
    @Delete
    suspend fun deleteCard(card: Card)
    @Delete
    suspend fun deleteCards(cards: List<Card>)
}