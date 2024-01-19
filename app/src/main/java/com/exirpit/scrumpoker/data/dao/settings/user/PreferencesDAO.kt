package com.exirpit.scrumpoker.data.dao.settings.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.exirpit.scrumpoker.domain.model.settings.user.Preferences

@Dao
interface PreferencesDAO {
    @Query("SELECT * FROM Preferences WHERE id = 1")
    fun getPreferences(): Preferences?
    @Upsert
    suspend fun upsertPreferences(preferences: Preferences)
    @Delete
    suspend fun deletePreferences(preferences: Preferences)
}