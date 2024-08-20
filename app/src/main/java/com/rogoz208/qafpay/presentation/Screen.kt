package com.rogoz208.qafpay.presentation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object AuthScreen : Screen()

    @Serializable
    data object MainBottomNavigationScreen : Screen()
}
