package com.exirpit.scrumpoker.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.exirpit.scrumpoker.data.db.entities.card.CardEntity
import com.exirpit.scrumpoker.data.db.entities.card.CardDeckEntity

data class CardDeckWithCards(
    @Embedded val cardDeck: CardDeckEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cardDeckId"
    )
    val cards: List<CardEntity>
)