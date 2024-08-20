package com.rogoz208.qafpay.domain.model

sealed class Data {

    data class SessionTestData(
        val userId: String
    ) : Data()

    data class SessionOpenData(
        val serverTime: Int,
        val sessionId: String
    ) : Data()

    data class OtpSendData(
        val code: Int,
        val length: Int,
        val otp: String,
        val timeLeft: Int?,
        val type: String
    ) : Data()

    data class OtpVerifyData(
        val attempts: Int?,
        val firstAuth: Boolean
    ) : Data()
}