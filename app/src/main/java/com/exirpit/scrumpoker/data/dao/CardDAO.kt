package com.exirpit.scrumpoker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {
    @Query("SELECT * FROM card")
    fun getAllCards(): Flow<List<Card>>
    @Query("SELECT * FROM card WHERE type = :type")
    fun getSpecificCards(type: CardType): List<Card>?
    @Upsert
    suspend fun upsertCards(cards: List<Card>)
    @Delete
    suspend fun deleteCard(card: Card)
    @Delete
    suspend fun deleteCards(cards: List<Card>)
}