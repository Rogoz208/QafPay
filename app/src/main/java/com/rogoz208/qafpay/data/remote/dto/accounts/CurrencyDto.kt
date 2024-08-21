package com.rogoz208.qafpay.data.remote.dto.accounts

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Currency

data class CurrencyDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
)

fun CurrencyDto.toCurrency(): Currency{
    return Currency(code = code, id = id, name = name, symbol = symbol)
}