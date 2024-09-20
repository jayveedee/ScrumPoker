package com.exirpit.scrumpoker

import android.app.Application
import androidx.room.Room
import com.exirpit.scrumpoker.data.db.ScrumPokerDatabase
import com.exirpit.scrumpoker.data.db.dao.CardDAO
import com.exirpit.scrumpoker.data.db.entities.card.Card
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScrumPokerModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ScrumPokerDatabase {
        return Room.databaseBuilder(
            app,
            ScrumPokerDatabase::class.java,
            ScrumPokerDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideCardDAO(db: ScrumPokerDatabase): CardDAO {
        return db.cardDAO
    }

    @Provides
    fun provideCardRepository(cardDAO: CardDAO) : ICardRepository {
        return CardRepository(cardDAO)
    }
}