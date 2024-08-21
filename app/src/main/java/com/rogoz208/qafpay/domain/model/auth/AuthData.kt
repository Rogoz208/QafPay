package com.rogoz208.qafpay.domain.model.auth

sealed class AuthData {

    data class SessionTestAuthData(
        val userId: String
    ) : AuthData()

    data class SessionOpenAuthData(
        val serverTime: Int,
        val sessionId: String
    ) : AuthData()

    data class OtpSendAuthData(
        val code: Int,
        val length: Int,
        val otp: String,
        val timeLeft: Int?,
        val type: String
    ) : AuthData()

    data class OtpVerifyAuthData(
        val attempts: Int?,
        val firstAuth: Boolean
    ) : AuthData()
}