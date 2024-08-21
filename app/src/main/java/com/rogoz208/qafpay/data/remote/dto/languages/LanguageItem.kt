package com.rogoz208.qafpay.data.remote.dto.languages

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Language

data class LanguageItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("language")
    val language: String
)

fun LanguageItem.toLanguage(): Language {
    return Language(code = code, language = language)
}