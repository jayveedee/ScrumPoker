package com.exirpit.scrumpoker.di

import com.exirpit.scrumpoker.data.db.dao.CardDAO
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCardRepository(cardDAO: CardDAO) : ICardRepository {
        return CardRepository(cardDAO)
    }
}