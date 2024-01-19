package com.exirpit.scrumpoker.domain.model.settings.launch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LaunchInfo(
    val defaultDataInserted: Boolean,
    val onboardingCompleted: Boolean,
    @PrimaryKey(autoGenerate = false) val id: Int = 1
)
