package com.rogoz208.qafpay.data.remote.dto.languages

import com.google.gson.annotations.SerializedName

data class LanguagesData(
    @SerializedName("items")
    val items: List<LanguageItem>
)