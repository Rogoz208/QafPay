package com.rogoz208.qafpay.domain.model

data class City(
    val id: String,
    val name: String,
    val lat: Int?,
    val lng: Int?,
    val countryId: String
)