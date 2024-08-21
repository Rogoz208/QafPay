package com.rogoz208.qafpay.domain.model.profile

import com.rogoz208.qafpay.domain.model.Status

data class Profile(
    val code: Int,
    val data: ProfileData,
    val message: List<String?>?,
    val requestId: String,
    val status: Status,
    val userMessage: String
)