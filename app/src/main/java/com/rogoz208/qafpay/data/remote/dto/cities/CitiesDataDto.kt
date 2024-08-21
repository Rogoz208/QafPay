package com.rogoz208.qafpay.data.remote.dto.cities

import com.google.gson.annotations.SerializedName

data class CitiesDataDto(
    @SerializedName("items")
    val items: List<CityItemDto>
)