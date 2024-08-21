package com.rogoz208.qafpay.data.remote.dto.profile

import com.google.gson.annotations.SerializedName
import com.rogoz208.qafpay.data.remote.dto.StatusDto
import com.rogoz208.qafpay.domain.model.Profile

data class ProfileDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: ProfileDataDto,
    @SerializedName("message")
    val message: List<String?>?,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("status")
    val status: StatusDto,
    @SerializedName("user_message")
    val userMessage: String
)

fun ProfileDto.toProfile(): Profile {
    return data.toProfileData()
}