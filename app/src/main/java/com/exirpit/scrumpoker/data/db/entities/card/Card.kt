package com.exirpit.scrumpoker.data.db.entities.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    val title: String = "",
    val type: CardType = CardType.Custom,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)