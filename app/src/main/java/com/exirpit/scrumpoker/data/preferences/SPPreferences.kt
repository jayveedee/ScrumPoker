package com.exirpit.scrumpoker.data.preferences

import android.content.SharedPreferences
import com.exirpit.scrumpoker.data.db.entities.card.CardEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckType
import com.exirpit.scrumpoker.domain.repository.ICardDeckRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SPPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val cardDeckRepository: ICardDeckRepository
) {
    //--------------------------------------------------------------------------------------------//
    // Companion object
    //--------------------------------------------------------------------------------------------//
    companion object {
        const val SHARED_PREFERENCES_KEY = "SCRUM_POKER"
        private const val KEY_FIRST_RUN = "KEY_FIRST_RUN"
    }

    //--------------------------------------------------------------------------------------------//
    // Initializer
    //--------------------------------------------------------------------------------------------//
    init {
        if (isFirstRun()) {
            CoroutineScope(Dispatchers.IO).launch {
                val cardDecks = createDefaultCardDecks()
                val cards = createDefaultCards(
                    cardDecks[0].id!!,
                    cardDecks[1].id!!,
                    cardDecks[2].id!!
                )

                cardDeckRepository.upsertCardDecks(cardDecks)
                cardDeckRepository.upsertCards(cards)
            }

            setFirstRun()
        }
    }

    //--------------------------------------------------------------------------------------------//
    // Private methods
    //--------------------------------------------------------------------------------------------//
    private fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_RUN, true)
    }

    private fun setFirstRun() {
        sharedPreferences
            .edit()
            .putBoolean(KEY_FIRST_RUN, false)
            .apply()
    }

    private fun createDefaultCards(standardDeckId: Int, fibonacciDeckId: Int, primesDeckId: Int): List<CardEntity> {
        val standardCards = listOf(
            CardEntity(cardDeckId = standardDeckId, title = "1/2"),
            CardEntity(cardDeckId = standardDeckId, title = "1"  ),
            CardEntity(cardDeckId = standardDeckId, title = "2"  ),
            CardEntity(cardDeckId = standardDeckId, title = "3"  ),
            CardEntity(cardDeckId = standardDeckId, title = "5"  ),
            CardEntity(cardDeckId = standardDeckId, title = "8"  ),
            CardEntity(cardDeckId = standardDeckId, title = "13" ),
            CardEntity(cardDeckId = standardDeckId, title = "20" ),
            CardEntity(cardDeckId = standardDeckId, title = "40" ),
            CardEntity(cardDeckId = standardDeckId, title = "100"),
            CardEntity(cardDeckId = standardDeckId, title = "?"  ),
            CardEntity(cardDeckId = standardDeckId, title = "...")
        )

        val fibonacciCards = listOf(
            CardEntity(cardDeckId = fibonacciDeckId, title = "1"),
            CardEntity(cardDeckId = fibonacciDeckId, title = "2"  ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "3"  ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "5"  ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "8"  ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "13"  ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "21" ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "34" ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "55" ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "89"),
            CardEntity(cardDeckId = fibonacciDeckId, title = "?"  ),
            CardEntity(cardDeckId = fibonacciDeckId, title = "...")
        )

        val primesCards = listOf(
            CardEntity(cardDeckId = primesDeckId, title = "2"),
            CardEntity(cardDeckId = primesDeckId, title = "3"  ),
            CardEntity(cardDeckId = primesDeckId, title = "5"  ),
            CardEntity(cardDeckId = primesDeckId, title = "7"  ),
            CardEntity(cardDeckId = primesDeckId, title = "11"  ),
            CardEntity(cardDeckId = primesDeckId, title = "13"  ),
            CardEntity(cardDeckId = primesDeckId, title = "17" ),
            CardEntity(cardDeckId = primesDeckId, title = "19" ),
            CardEntity(cardDeckId = primesDeckId, title = "23" ),
            CardEntity(cardDeckId = primesDeckId, title = "29"),
            CardEntity(cardDeckId = primesDeckId, title = "?"  ),
            CardEntity(cardDeckId = primesDeckId, title = "...")
        )

        return standardCards + fibonacciCards + primesCards
    }

    private fun createDefaultCardDecks(): List<CardDeckEntity> {
        val standard = CardDeckEntity(
            id = 1,
            title = "Standard", //TODO localize
            description = "The usual SCRUM Card deck", //TODO localize
            type = CardDeckType.Default,
            isMainCardDeck = true
        )

        val fibonacci = CardDeckEntity(
            id = 2,
            title = "Fibonacci", //TODO localize
            description = "The Fibonacci sequence Card deck", //TODO localize
            type = CardDeckType.Default
        )

        val primes = CardDeckEntity(
            id = 3,
            title = "Primes", //TODO localize
            description = "The Prime sequence Card deck", //TODO localize
            type = CardDeckType.Default
        )

        return listOf(
            standard,
            fibonacci,
            primes
        )
    }
}