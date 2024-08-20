package com.rogoz208.qafpay.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Auth

data class AuthDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val dataDto: DataDto,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: StatusDto,
    @SerializedName("user_message")
    val userMessage: String
)

fun AuthDto.toAuth(): Auth {
    return Auth(
        code = code,
        data = dataDto.toData(),
        message = message,
        requestId = requestId,
        status = status.toStatus(),
        userMessage = userMessage
    )
}