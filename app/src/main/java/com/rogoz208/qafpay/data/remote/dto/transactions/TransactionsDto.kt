package com.rogoz208.qafpay.data.remote.dto.transactions

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Transaction

data class TransactionsDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: TransactionsDataDto,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("user_message")
    val userMessage: String
)

fun TransactionsDto.toTransactionsList(): List<Transaction> {
    return data.items.map { it.toTransaction() }
}