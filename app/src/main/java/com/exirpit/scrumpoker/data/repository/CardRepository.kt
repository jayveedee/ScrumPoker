package com.exirpit.scrumpoker.data.repository

import androidx.compose.runtime.collectAsState
import com.exirpit.scrumpoker.data.dao.card.CardDAO
import com.exirpit.scrumpoker.data.dao.settings.launch.LaunchInfoDAO
import com.exirpit.scrumpoker.data.dao.settings.user.PreferencesDAO
import com.exirpit.scrumpoker.domain.model.card.Card
import com.exirpit.scrumpoker.domain.model.card.CardType
import com.exirpit.scrumpoker.domain.repository.ICardRepository
import kotlinx.coroutines.flow.collect

class CardRepository(
    private val cardDAO: CardDAO,
    private val preferencesDAO: PreferencesDAO
) : ICardRepository {

    override fun getMainScreenCards(): List<Card> {
        val preferences = preferencesDAO.getPreferences() ?: return getFibonacciCards()

        return if (preferences.preferredCards == CardType.Fibonacci)
            getFibonacciCards()
        else
            getPrimeCards()
    }

    override fun getFibonacciCards(): List<Card> {
        return cardDAO.getSpecificCards(CardType.Fibonacci) ?: emptyList()
    }

    override fun getPrimeCards(): List<Card> {
        return cardDAO.getSpecificCards(CardType.Prime) ?: emptyList()
    }
}