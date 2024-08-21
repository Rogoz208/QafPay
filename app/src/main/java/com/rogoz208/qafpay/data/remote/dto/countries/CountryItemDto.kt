package com.rogoz208.qafpay.data.remote.dto.countries

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Country

data class CountryItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)

fun CountryItemDto.toCountry(): Country {
    return Country(id = id, name = name, lat = lat, lng = lng)
}