package com.rogoz208.qafpay.presentation.screen_transactions

import com.rogoz208.qafpay.domain.model.Transaction

data class TransactionsState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val transactions: List<Transaction> = listOf(),
    val accountId: String = ""
)
