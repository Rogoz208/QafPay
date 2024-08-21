package com.rogoz208.qafpay.data.remote.dto

import com.google.gson.annotations.SerializedName

enum class StatusDto(value: String) {
    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("error")
    ERROR("error")
}