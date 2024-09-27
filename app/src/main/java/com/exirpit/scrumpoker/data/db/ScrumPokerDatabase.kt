package com.exirpit.scrumpoker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exirpit.scrumpoker.data.db.dao.CardDAO
import com.exirpit.scrumpoker.data.db.entities.card.Card

@Database(
    entities =
        [
            Card::class
        ],
    version = 1
)
abstract class ScrumPokerDatabase: RoomDatabase() {

    abstract val cardDAO: CardDAO

    companion object {
        const val DATABASE_NAME = "ScrumPokerDatabase"
    }
}