package com.rogoz208.qafpay.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Data

sealed class DataDto {

    data class SessionTestDataDto(
        @SerializedName("user_id") val userId: String
    ) : DataDto()

    data class SessionOpenDataDto(
        @SerializedName("server_time") val serverTime: Int,
        @SerializedName("session_id") val sessionId: String
    ) : DataDto()

    data class OtpSendDataDto(
        @SerializedName("code") val code: Int,
        @SerializedName("length") val length: Int,
        @SerializedName("otp") val otp: String,
        @SerializedName("time_left") val timeLeft: Int?,
        @SerializedName("type") val type: String
    ) : DataDto()

    data class OtpVerifyDataDto(
        @SerializedName("attempts") val attempts: Int?,
        @SerializedName("first_auth") val firstAuth: Boolean
    ) : DataDto()
}

fun DataDto.toData(): Data = when (this) {
    is DataDto.OtpSendDataDto -> Data.OtpSendData(code, length, otp, timeLeft, type)
    is DataDto.OtpVerifyDataDto -> Data.OtpVerifyData(attempts, firstAuth)
    is DataDto.SessionOpenDataDto -> Data.SessionOpenData(serverTime, sessionId)
    is DataDto.SessionTestDataDto -> Data.SessionTestData(userId)
}
