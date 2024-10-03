package com.exirpit.scrumpoker

import android.app.Application
import com.exirpit.scrumpoker.data.preferences.SPPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SPApplication: Application() {

    @Inject lateinit var preferences: SPPreferences

    override fun onCreate() {
        super.onCreate()

        preferences.initializePreferences()
    }
}