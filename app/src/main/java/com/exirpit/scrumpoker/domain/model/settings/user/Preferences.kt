package com.exirpit.scrumpoker.domain.model.settings.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exirpit.scrumpoker.domain.model.card.CardType

@Entity
data class Preferences(
    val preferredCards: CardType,
    @PrimaryKey(autoGenerate = false) val id: Int = 1
)
