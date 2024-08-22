package com.rogoz208.qafpay.domain.model

data class Account(
    val balance: Double,
    val createdAt: String,
    val currency: Currency,
    val id: String,
    val name: String,
    val paymentAvailable: Boolean,
    val transferAvailable: Boolean,
    val type: String,
    val updatedAt: String,
    val userId: String
)