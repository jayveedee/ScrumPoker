package com.exirpit.scrumpoker.di

import android.app.Application
import androidx.room.Room
import com.exirpit.scrumpoker.data.db.ScrumPokerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ScrumPokerDatabase {
        return Room.databaseBuilder(
            app,
            ScrumPokerDatabase::class.java,
            ScrumPokerDatabase.DATABASE_NAME
        ).build()
    }
}