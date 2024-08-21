package com.rogoz208.qafpay.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

data class UpdateUserProfileRequest(
    @SerializedName("city_id")
    val cityId: String,
    @SerializedName("country_id")
    val countryId: String,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("source")
    val source: String = "email"
)
