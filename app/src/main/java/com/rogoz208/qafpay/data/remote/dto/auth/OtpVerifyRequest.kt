package com.rogoz208.qafpay.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class OtpVerifyRequest(
    @SerializedName("otp")
    val otp: String
)
