package com.rogoz208.qafpay.presentation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object AuthScreen : Screen()

    @Serializable
    data object BottomNavigationScreen : Screen()

    @Serializable
    data object AccountsScreen : Screen()

    @Serializable
    data class TransactionsScreen(val accountId: String) : Screen()

    @Serializable
    data object ProfileScreen : Screen()
}
