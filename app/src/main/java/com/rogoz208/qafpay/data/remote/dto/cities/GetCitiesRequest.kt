package com.rogoz208.qafpay.data.remote.dto.cities

import com.google.gson.annotations.SerializedName

data class GetCitiesRequest(
    @SerializedName("country_id")
    val countryId: String = "",
    @SerializedName("search")
    val search: String = ""
)
