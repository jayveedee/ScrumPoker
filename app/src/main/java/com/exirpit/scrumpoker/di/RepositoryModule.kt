package com.exirpit.scrumpoker.di

import com.exirpit.scrumpoker.data.db.dao.ICardDeckDAO
import com.exirpit.scrumpoker.data.repository.CardDeckRepository
import com.exirpit.scrumpoker.domain.repository.ICardDeckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCardDeckRepository(cardDeckDAO: ICardDeckDAO) : ICardDeckRepository {
        return CardDeckRepository(cardDeckDAO)
    }
}