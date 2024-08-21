package com.rogoz208.qafpay.data.remote.dto.auth

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.auth.AuthData

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

fun AuthDataDto.toAuthData(): AuthData = when (this) {
    is AuthDataDto.OtpSendAuthDataDto -> AuthData.OtpSendAuthData(
        code = code,
        length = length,
        otp = otp,
        timeLeft = timeLeft,
        type = type
    )

    is AuthDataDto.OtpVerifyAuthDataDto -> AuthData.OtpVerifyAuthData(
        attempts = attempts,
        firstAuth = firstAuth
    )

    is AuthDataDto.SessionOpenAuthDataDto -> AuthData.SessionOpenAuthData(
        serverTime = serverTime,
        sessionId = sessionId
    )

    is AuthDataDto.SessionTestAuthDataDto -> AuthData.SessionTestAuthData(
        userId = userId
    )
}
