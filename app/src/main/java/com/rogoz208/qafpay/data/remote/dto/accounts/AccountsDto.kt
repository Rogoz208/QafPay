package com.rogoz208.qafpay.data.remote.dto.accounts

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Account

data class AccountsDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: AccountsDataDto,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("user_message")
    val userMessage: String
)

fun AccountsDto.toAccountsList(): List<Account> {
    return data.items.map { it.toAccount() }
}