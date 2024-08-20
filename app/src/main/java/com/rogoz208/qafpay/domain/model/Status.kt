package com.rogoz208.qafpay.domain.model

import com.google.gson.annotations.SerializedName

enum class Status(value: String) {
    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("error")
    ERROR("error")
}