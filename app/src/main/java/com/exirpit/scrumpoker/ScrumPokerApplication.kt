package com.exirpit.scrumpoker

import android.app.Application

class ScrumPokerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ScrumPokerApplicationModule(this)
    }

    companion object {
        lateinit var appModule: ScrumPokerApplicationModule
    }
}