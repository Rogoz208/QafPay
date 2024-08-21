package com.rogoz208.qafpay.data.remote.dto.languages

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Language

data class LanguagesDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: LanguagesData,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("user_message")
    val userMessage: String
)

fun LanguagesDto.toLanguagesList(): List<Language> {
    return data.items.map { it.toLanguage() }
}