package com.exirpit.scrumpoker.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exirpit.scrumpoker.domain.model.Preferences

@Dao
interface PreferencesDAO {

    @Query("SELECT * FROM Preferences WHERE id = 1")
    fun getPreferences(): Preferences?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreferences(preferences: Preferences)
}