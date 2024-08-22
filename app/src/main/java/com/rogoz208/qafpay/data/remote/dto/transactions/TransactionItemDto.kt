package com.rogoz208.qafpay.data.remote.dto.transactions

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.data.remote.dto.CurrencyDto
import com.rogoz208.qafpay.data.remote.dto.toCurrency
import com.rogoz208.qafpay.domain.model.Transaction

data class TransactionItemDto(
    @SerializedName("account_amount")
    val accountAmount: Double?,
    @SerializedName("account_currency")
    val accountCurrency: CurrencyDto?,
    @SerializedName("account_id")
    val accountId: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("currency")
    val currency: CurrencyDto?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("operation_id")
    val operationId: String?,
    @SerializedName("service")
    val service: ServiceDto?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user_id")
    val userId: String?
)

fun TransactionItemDto.toTransaction(): Transaction {
    return Transaction(
        accountAmount = accountAmount,
        accountCurrency = accountCurrency?.toCurrency(),
        accountId = accountId,
        amount = amount,
        createdAt = createdAt,
        currency = currency?.toCurrency(),
        description = description ?: "",
        id = id,
        operationId = operationId,
        service = service?.toService(),
        status = status ?: "",
        type = type ?: "",
        updatedAt = updatedAt,
        userId = userId
    )
}