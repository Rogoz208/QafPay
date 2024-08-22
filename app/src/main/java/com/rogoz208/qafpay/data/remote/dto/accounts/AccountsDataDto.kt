package com.rogoz208.qafpay.data.remote.dto.accounts

import com.google.gson.annotations.SerializedName

data class AccountsDataDto(
    @SerializedName("items")
    val items: List<AccountItemDto>
)