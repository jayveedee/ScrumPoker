package com.exirpit.scrumpoker.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exirpit.scrumpoker.data.dao.CardDAO
import com.exirpit.scrumpoker.data.dao.LaunchInfoDAO
import com.exirpit.scrumpoker.data.dao.PreferencesDAO
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.settings.launch.LaunchInfo
import com.exirpit.scrumpoker.domain.model.settings.user.Preferences

@Database(
    entities =
        [
            LaunchInfo::class,
            Preferences::class,
            Card::class
        ],
    version = 1
)
abstract class ScrumPokerDatabase: RoomDatabase() {

    abstract val cardDAO: CardDAO
    abstract val launchInfoDAO: LaunchInfoDAO
    abstract val preferencesDAO: PreferencesDAO

    companion object {
        const val DATABASE_NAME = "ScrumPokerDatabase"
    }
}