package com.rogoz208.qafpay.domain.model.auth

import com.rogoz208.qafpay.domain.model.Status

data class Auth(
    val code: Int,
    val authData: AuthData?,
    val message: List<String?>?,
    val requestId: String,
    val status: Status,
    val userMessage: String
)