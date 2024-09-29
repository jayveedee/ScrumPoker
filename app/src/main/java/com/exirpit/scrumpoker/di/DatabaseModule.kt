package com.exirpit.scrumpoker.di

import android.app.Application
import androidx.room.Room
import com.exirpit.scrumpoker.data.db.SPDatabase
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
    fun provideDatabase(app: Application): SPDatabase {
        return Room.databaseBuilder(
            app,
            SPDatabase::class.java,
            SPDatabase.DATABASE_NAME
        ).build()
    }
}