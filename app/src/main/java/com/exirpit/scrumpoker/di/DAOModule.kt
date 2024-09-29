package com.exirpit.scrumpoker.di

import com.exirpit.scrumpoker.data.db.ScrumPokerDatabase
import com.exirpit.scrumpoker.data.db.dao.CardDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DAOModule {
    @Provides
    fun provideCardDAO(db: ScrumPokerDatabase): CardDAO {
        return db.cardDAO
    }
}