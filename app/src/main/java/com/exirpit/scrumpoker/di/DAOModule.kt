package com.exirpit.scrumpoker.di

import com.exirpit.scrumpoker.data.db.SPDatabase
import com.exirpit.scrumpoker.data.db.dao.ICardDeckDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DAOModule {
    @Provides
    fun provideCardDeckDAO(db: SPDatabase): ICardDeckDAO {
        return db.cardDeckDAO
    }
}