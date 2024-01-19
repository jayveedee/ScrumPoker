package com.exirpit.scrumpoker

import android.content.Context
import androidx.room.Room
import com.exirpit.scrumpoker.core.ScrumPokerDatabase
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import com.exirpit.scrumpoker.domain.model.settings.user.Preferences
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import kotlinx.coroutines.runBlocking

class ScrumPokerApplicationModule(
    private val context: Context
) {
    private val database: ScrumPokerDatabase

    // repositories
    val cardRepository: ICardRepository

    init {
        database = createDatabase()
        setupDatabase()

        cardRepository = CardRepository(
            database.cardDAO,
            database.preferencesDAO
        )
    }

    private fun createDatabase(): ScrumPokerDatabase {
        return Room.databaseBuilder(
            context,
            ScrumPokerDatabase::class.java,
            ScrumPokerDatabase.DATABASE_NAME
        ).build()
    }

    private fun setupDatabase() {
        runBlocking {
            val launchInfo = database.launchInfoDAO.getLaunchInfo()

            if (!launchInfo.defaultDataInserted) {
                database.cardDAO.upsertCards(
                    listOf(
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
                )
                database.cardDAO.upsertCards(
                    listOf(
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

                database.launchInfoDAO.upsertLaunchInfo(
                    launchInfo.copy(
                        defaultDataInserted = true
                    )
                )

                database.preferencesDAO.upsertPreferences(
                    Preferences(
                        CardType.Fibonacci
                    )
                )
            }
        }
    }
}