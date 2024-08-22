package com.rogoz208.qafpay.domain.model

data class Service(
    val currency: Currency,
    val id: String,
    val name: String,
    val type: String
)