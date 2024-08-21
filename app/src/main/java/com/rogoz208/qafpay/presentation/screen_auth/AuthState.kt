package com.rogoz208.qafpay.presentation.screen_auth

import com.rogoz208.qafpay.domain.model.Auth

data class AuthState(
    val isSessionOpened: Boolean = false,
    val isOtpSent: Boolean = false,
    val isOtpVerified: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val email: String = "",
    val enteredOtp: String = "",
    val auth: Auth? = null
)
