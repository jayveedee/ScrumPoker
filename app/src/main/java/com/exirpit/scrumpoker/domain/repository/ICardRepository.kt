package com.exirpit.scrumpoker.domain.repository

import com.exirpit.scrumpoker.domain.model.card.Card
import kotlinx.coroutines.flow.StateFlow

interface ICardRepository {
    suspend fun getMainScreenCards(): List<Card>
    suspend fun getFibonacciCards(): List<Card>
    suspend fun getStandardCards(): List<Card>
}