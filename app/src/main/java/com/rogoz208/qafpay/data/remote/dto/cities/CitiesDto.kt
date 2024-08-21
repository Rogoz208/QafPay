package com.rogoz208.qafpay.data.remote.dto.cities

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.City

data class CitiesDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: CitiesDataDto,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("user_message")
    val userMessage: String
)

fun CitiesDto.toCitiesList(): List<City> {
    return data.items.map { it.toCity() }
}