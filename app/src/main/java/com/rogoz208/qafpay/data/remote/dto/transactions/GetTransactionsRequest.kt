package com.rogoz208.qafpay.data.remote.dto.transactions

import com.google.gson.annotations.SerializedName

data class GetTransactionsRequest(
    @SerializedName("account_id")
    val accountId: String
)
