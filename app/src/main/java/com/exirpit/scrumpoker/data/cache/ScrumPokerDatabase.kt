package com.exirpit.scrumpoker.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exirpit.scrumpoker.data.cache.dao.CardDAO
import com.exirpit.scrumpoker.data.cache.dao.PreferencesDAO
import com.exirpit.scrumpoker.domain.model.Preferences
import com.exirpit.scrumpoker.domain.model.card.Card

@Database(
    entities = [Card::class, Preferences::class],
    version = 1
)
abstract class ScrumPokerDatabase: RoomDatabase() {

    abstract val cardDAO: CardDAO
    abstract val preferencesDAO: PreferencesDAO

    companion object {
        const val DATABASE_NAME = "ScrumPokerDatabase"
    }
}