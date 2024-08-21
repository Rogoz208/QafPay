package com.rogoz208.qafpay.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

class OtpSendRequest(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("source")
    val source: String = "email"
)