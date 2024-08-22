package com.rogoz208.qafpay.data.remote.dto.transactions

import com.google.gson.annotations.SerializedName

data class TransactionsDataDto(
    @SerializedName("items")
    val items: List<TransactionItemDto>
)