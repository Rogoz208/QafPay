package com.rogoz208.qafpay.domain.model

data class Profile(
    val blocked: Boolean?,
    val city: String,
    val cityId: String?,
    val country: String,
    val countryId: String?,
    val createdAt: String?,
    val email: String,
    val geoLat: Double?,
    val geoLng: Double?,
    val geoMaxRadius: Int?,
    val geoRadius: Int?,
    val id: String,
    val language: String,
    val loggedIn: Boolean?,
    val name: String,
    val phone: String?,
    val referralUserId: String?,
    val updatedAt: String?
)