package com.exirpit.scrumpoker.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {

    @Query("SELECT * FROM card")
    fun getAllCards(): Flow<List<Card>>

    @Query("SELECT * FROM card WHERE type = :type")
    fun getSpecificCards(type: CardType): List<Card>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<Card>)

    @Delete
    suspend fun deleteCard(card: Card)

    @Delete
    suspend fun deleteCards(cards: List<Card>)
}