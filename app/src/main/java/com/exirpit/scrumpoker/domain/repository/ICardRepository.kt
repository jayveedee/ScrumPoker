package com.exirpit.scrumpoker.domain.repository

import com.exirpit.scrumpoker.domain.model.card.Card

interface ICardRepository {
    fun getFibonacciCards(): List<Card>
    fun getPrimeCards(): List<Card>
}