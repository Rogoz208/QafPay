package com.rogoz208.qafpay.data.remote.dto.countries

import com.google.gson.annotations.SerializedName

data class GetAllCountriesRequest(
    @SerializedName("search")
    val search: String = ""
)
