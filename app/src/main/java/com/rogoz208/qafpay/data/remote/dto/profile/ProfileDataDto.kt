package com.rogoz208.qafpay.data.remote.dto.profile

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.domain.model.Profile

data class ProfileDataDto(
    @SerializedName("blocked") val blocked: Boolean?,
    @SerializedName("city") val city: String?,
    @SerializedName("city_id") val cityId: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("country_id") val countryId: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("geo_lat") val geoLat: Double?,
    @SerializedName("geo_lng") val geoLng: Double?,
    @SerializedName("geo_max_radius") val geoMaxRadius: Int?,
    @SerializedName("geo_radius") val geoRadius: Int?,
    @SerializedName("id") val id: String,
    @SerializedName("language") val language: String?,
    @SerializedName("logged_in") val loggedIn: Boolean?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("referral_user_id") val referralUserId: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

fun ProfileDataDto.toProfileData(): Profile {
    return Profile(
        blocked = blocked,
        city = city ?: "Undefined",
        cityId = cityId,
        country = country ?: "Undefined",
        countryId = countryId,
        createdAt = createdAt,
        email = email ?: "Undefined",
        geoLat = geoLat,
        geoLng = geoLng,
        geoMaxRadius = geoMaxRadius,
        geoRadius = geoRadius,
        id = id,
        language = language ?: "Undefined",
        loggedIn = loggedIn,
        name = name ?: "Undefined",
        phone = phone,
        referralUserId = referralUserId,
        updatedAt = updatedAt
    )
}