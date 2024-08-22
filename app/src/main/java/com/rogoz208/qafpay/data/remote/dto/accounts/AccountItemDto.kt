package com.rogoz208.qafpay.data.remote.dto.accounts

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.data.remote.dto.CurrencyDto
import com.rogoz208.qafpay.data.remote.dto.toCurrency
import com.rogoz208.qafpay.domain.model.Account

data class AccountItemDto(
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("currency")
    val currency: CurrencyDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("payment_available")
    val paymentAvailable: Boolean,
    @SerializedName("transfer_available")
    val transferAvailable: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)

fun AccountItemDto.toAccount(): Account {
    return Account(
        balance = balance,
        createdAt = createdAt,
        currency = currency.toCurrency(),
        id = id,
        name = name,
        paymentAvailable = paymentAvailable,
        transferAvailable = transferAvailable,
        type = type,
        updatedAt = updatedAt,
        userId = userId
    )
}