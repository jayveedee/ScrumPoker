package com.exirpit.scrumpoker.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.exirpit.scrumpoker.data.preferences.SPPreferences
import com.exirpit.scrumpoker.domain.repository.ICardDeckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Provides
    @Singleton
    fun providesSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(
            SPPreferences.SHARED_PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providesPreferences(preferences: SharedPreferences, cardDeckRepository: ICardDeckRepository): SPPreferences {
        return SPPreferences(preferences, cardDeckRepository)
    }
}