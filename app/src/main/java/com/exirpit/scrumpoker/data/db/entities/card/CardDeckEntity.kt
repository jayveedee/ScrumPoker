package com.exirpit.scrumpoker.data.db.entities.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardDeckEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String = "",
    val type: CardDeckType = CardDeckType.Custom,
    val isMainCardDeck: Boolean = false
)