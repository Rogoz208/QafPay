package com.rogoz208.qafpay.domain.model

data class Auth(
    val code: Int,
    val data: Data?,
    val message: List<String?>?,
    val requestId: String,
    val status: Status,
    val userMessage: String
)