package com.rogoz208.qafpay.domain.model

sealed class Auth {

    data class SessionTestAuth(
        val userId: String
    ) : Auth()

    data class SessionOpenAuth(
        val serverTime: Int,
        val sessionId: String
    ) : Auth()

    data class OtpSendAuth(
        val code: Int,
        val length: Int,
        val otp: String,
        val timeLeft: Int?,
        val type: String
    ) : Auth()

    data class OtpVerifyAuth(
        val attempts: Int?,
        val firstAuth: Boolean
    ) : Auth()
}