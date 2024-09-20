package com.exirpit.scrumpoker

import android.app.Application
import androidx.room.Room
import com.exirpit.scrumpoker.data.db.ScrumPokerDatabase
import com.exirpit.scrumpoker.data.db.entities.card.Card
import com.exirpit.scrumpoker.data.db.entities.card.CardType
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class ScrumPokerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            initializeApplication()
        }
    }

    private suspend fun initializeApplication() {
        /*
        db = Room.databaseBuilder(
            applicationContext,
            ScrumPokerDatabase::class.java,
            ScrumPokerDatabase.DATABASE_NAME
        ).build()

        db.cardDAO.upsertCards(
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
                Card("89", CardType.Fibonacci),
                Card("?", CardType.Fibonacci),
                Card("...", CardType.Fibonacci),
            )
        )
        db.cardDAO.upsertCards(
            listOf(
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
        )*/
    }
}