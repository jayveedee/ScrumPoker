package com.exirpit.scrumpoker

import android.content.Context
import androidx.room.Room
import com.exirpit.scrumpoker.data.cache.ScrumPokerDatabase
import com.exirpit.scrumpoker.data.repository.CacheRepository
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import com.exirpit.scrumpoker.domain.repository.ICacheRepository
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class ScrumPokerApplicationModule(
    private val context: Context
) {
    private val database: ScrumPokerDatabase

    // repositories
    val cacheRepository: ICacheRepository
    val cardRepository: ICardRepository

    init {
        database = setupDatabase()

        cacheRepository = CacheRepository(database.preferencesDAO)
        cardRepository = CardRepository(database.cardDAO)

        if (cacheRepository.getHomeScreenCards().isEmpty()) {
            runBlocking {
                val defaultCards = setupDefaultScrumCards()
                defaultCards[0][CardType.Fibonacci]?.let { cacheRepository.setHomeScreenCards(it) }
                defaultCards[1][CardType.Prime]?.let { cacheRepository.setHomeScreenCards(it) }
            }
        }
    }

    private fun setupDatabase(): ScrumPokerDatabase {
        return Room.databaseBuilder(
            context,
            ScrumPokerDatabase::class.java,
            ScrumPokerDatabase.DATABASE_NAME
        ).build()
    }

    private fun setupDefaultScrumCards(): Array<Map<CardType, List<Card>>> {
        return arrayOf(
            mapOf(
                CardType.Fibonacci to listOf(
                    Card("1", CardType.Fibonacci),
                    Card("2", CardType.Fibonacci),
                    Card("3", CardType.Fibonacci),
                    Card("5", CardType.Fibonacci),
                    Card("8", CardType.Fibonacci),
                    Card("13", CardType.Fibonacci),
                    Card("21", CardType.Fibonacci),
                    Card("34", CardType.Fibonacci),
                    Card("55", CardType.Fibonacci),
                    Card("?", CardType.Fibonacci),
                    Card("...", CardType.Fibonacci),
                )
            ),
            mapOf(
                CardType.Prime to listOf(
                    Card("2", CardType.Prime),
                    Card("3", CardType.Prime),
                    Card("5", CardType.Prime),
                    Card("7", CardType.Prime),
                    Card("11", CardType.Prime),
                    Card("17", CardType.Prime),
                    Card("19", CardType.Prime),
                    Card("23", CardType.Prime),
                    Card("29", CardType.Prime),
                    Card("?", CardType.Prime),
                    Card("...", CardType.Prime),
                )
            )
        )
    }
}