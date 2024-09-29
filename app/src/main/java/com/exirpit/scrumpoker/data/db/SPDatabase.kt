package com.exirpit.scrumpoker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exirpit.scrumpoker.data.db.dao.ICardDeckDAO
import com.exirpit.scrumpoker.data.db.entities.card.CardEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity

@Database(
    entities =
        [
            CardEntity::class,
            CardDeckEntity::class
        ],
    version = 1
)
abstract class SPDatabase: RoomDatabase() {

    abstract val cardDeckDAO: ICardDeckDAO

    companion object {
        const val DATABASE_NAME = "ScrumPokerDatabase"
    }
}