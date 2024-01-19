package com.exirpit.scrumpoker.domain.model.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    val title: String,
    val type: CardType,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)