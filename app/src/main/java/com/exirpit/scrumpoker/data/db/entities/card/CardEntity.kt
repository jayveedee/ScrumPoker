package com.exirpit.scrumpoker.data.db.entities.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val cardDeckId: Int = 0,
    val title: String = ""
)