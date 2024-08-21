package com.rogoz208.qafpay.data.remote.dto.auth

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Auth

sealed class AuthDataDto {

    data class SessionTestAuthDataDto(
        @SerializedName("user_id") val userId: String
    ) : AuthDataDto()

    data class SessionOpenAuthDataDto(
        @SerializedName("server_time") val serverTime: Int,
        @SerializedName("session_id") val sessionId: String
    ) : AuthDataDto()

    data class OtpSendAuthDataDto(
        @SerializedName("code") val code: Int,
        @SerializedName("length") val length: Int,
        @SerializedName("otp") val otp: String,
        @SerializedName("time_left") val timeLeft: Int?,
        @SerializedName("type") val type: String
    ) : AuthDataDto()

    data class OtpVerifyAuthDataDto(
        @SerializedName("attempts") val attempts: Int?,
        @SerializedName("first_auth") val firstAuth: Boolean
    ) : AuthDataDto()
}

fun AuthDataDto.toAuthData(): Auth = when (this) {
    is AuthDataDto.OtpSendAuthDataDto -> Auth.OtpSendAuth(
        code = code,
        length = length,
        otp = otp,
        timeLeft = timeLeft,
        type = type
    )

    is AuthDataDto.OtpVerifyAuthDataDto -> Auth.OtpVerifyAuth(
        attempts = attempts,
        firstAuth = firstAuth
    )

    is AuthDataDto.SessionOpenAuthDataDto -> Auth.SessionOpenAuth(
        serverTime = serverTime,
        sessionId = sessionId
    )

    is AuthDataDto.SessionTestAuthDataDto -> Auth.SessionTestAuth(
        userId = userId
    )
}
