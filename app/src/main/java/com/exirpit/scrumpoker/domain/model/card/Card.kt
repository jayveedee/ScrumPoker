package com.exirpit.scrumpoker.domain.model.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    val title: String,
    val type: CardType,
    @PrimaryKey val id: Int? = null
)