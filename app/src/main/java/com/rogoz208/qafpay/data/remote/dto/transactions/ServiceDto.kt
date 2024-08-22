package com.rogoz208.qafpay.data.remote.dto.transactions

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.data.remote.dto.CurrencyDto
import com.rogoz208.qafpay.data.remote.dto.toCurrency
import com.rogoz208.qafpay.domain.model.Service

data class ServiceDto(
    @SerializedName("currency")
    val currency: CurrencyDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)

fun ServiceDto.toService(): Service {
    return Service(currency = currency.toCurrency(), id = id, name = name, type = type)
}