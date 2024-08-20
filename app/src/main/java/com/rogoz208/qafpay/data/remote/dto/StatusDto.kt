package com.rogoz208.qafpay.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Status

enum class StatusDto(value: String) {
    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("error")
    ERROR("error")
}

fun StatusDto.toStatus(): Status = when (this) {
    StatusDto.SUCCESS -> Status.SUCCESS
    StatusDto.ERROR -> Status.ERROR
}