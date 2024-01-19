package com.exirpit.scrumpoker.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.exirpit.scrumpoker.core.ScrumPokerDatabase
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import com.exirpit.scrumpoker.domain.model.settings.launch.LaunchInfo
import com.exirpit.scrumpoker.domain.model.settings.user.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _applicationLoadedStateFlow = MutableStateFlow(false)
    val applicationLoadedStateFlow = _applicationLoadedStateFlow.asStateFlow()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Public methods                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    fun onStartup(context: Context) {
        if (!_applicationLoadedStateFlow.value) {
            val database = Room.databaseBuilder(
                context,
                ScrumPokerDatabase::class.java,
                ScrumPokerDatabase.DATABASE_NAME
            ).build()

            CoroutineScope(Dispatchers.IO).launch {
                configureDatabase(database)
                _applicationLoadedStateFlow.update { true }
            }

            db = database
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Private methods                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private suspend fun configureDatabase(database: ScrumPokerDatabase) {
        val launchInfo = database.launchInfoDAO.getLaunchInfo()

        if (launchInfo == null || !launchInfo.defaultDataInserted) {
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
                LaunchInfo(
                    defaultDataInserted = true,
                    onboardingCompleted = false
                )
            )

            database.preferencesDAO.upsertPreferences(
                Preferences(
                    CardType.Fibonacci
                )
            )
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Companion object                                                                           //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    companion object {
        lateinit var db: ScrumPokerDatabase
    }
}