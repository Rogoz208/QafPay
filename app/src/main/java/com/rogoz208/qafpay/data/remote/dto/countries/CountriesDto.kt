package com.rogoz208.qafpay.data.remote.dto.countries

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.data.remote.dto.StatusDto
import com.rogoz208.qafpay.domain.model.Country

data class CountriesDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: CountriesDataDto,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: StatusDto,
    @SerializedName("user_message")
    val userMessage: String
)

fun CountriesDto.toCountriesList(): List<Country> {
    return data.items.map { it.toCountry() }
}