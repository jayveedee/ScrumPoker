package com.exirpit.scrumpoker.core.converter

import androidx.room.TypeConverter
import com.exirpit.scrumpoker.domain.model.card.Card

class TypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Card> {
        return
    }
}