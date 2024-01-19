package com.exirpit.scrumpoker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.exirpit.scrumpoker.domain.model.settings.launch.LaunchInfo

@Dao
interface LaunchInfoDAO {
    @Query("SELECT * FROM LaunchInfo WHERE id = 1")
    fun getLaunchInfo(): LaunchInfo?
    @Upsert
    suspend fun upsertLaunchInfo(launchInfo: LaunchInfo)
    @Delete
    suspend fun deleteLaunchInfo(launchInfo: LaunchInfo)
}