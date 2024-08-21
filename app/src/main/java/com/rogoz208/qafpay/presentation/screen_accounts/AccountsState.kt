package com.rogoz208.qafpay.presentation.screen_accounts

import com.rogoz208.qafpay.domain.model.Account

data class AccountsState(
    val isLoading:Boolean = false,
    val errorMessage: String = "",
    val accounts: List<Account> = listOf()
)
