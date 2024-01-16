package com.exirpit.scrumpoker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exirpit.scrumpoker.domain.model.card.Card
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Preferences(
    val homeScreenCards: List<Card>,
    @PrimaryKey val id: Int = 0
)