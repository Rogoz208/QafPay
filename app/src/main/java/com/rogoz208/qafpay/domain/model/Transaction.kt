package com.rogoz208.qafpay.domain.model

data class Transaction(
    val accountAmount: Double?,
    val accountCurrency: Currency?,
    val accountId: String?,
    val amount: Double?,
    val createdAt: String?,
    val currency: Currency?,
    val description: String,
    val id: String?,
    val operationId: String?,
    val service: Service?,
    val status: String,
    val type: String,
    val updatedAt: String?,
    val userId: String?
)