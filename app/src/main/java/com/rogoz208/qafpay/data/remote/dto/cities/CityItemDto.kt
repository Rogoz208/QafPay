package com.rogoz208.qafpay.data.remote.dto.cities

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.City

data class CityItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val lat: Int?,
    @SerializedName("lng")
    val lng: Int?,
    @SerializedName("country_id")
    val countryId: String
)

fun CityItemDto.toCity(): City {
    return City(id = id, name = name, lat = lat, lng = lng, countryId = countryId)
}