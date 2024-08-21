package com.rogoz208.qafpay.domain.repos

import com.rogoz208.qafpay.data.remote.dto.auth.AuthDto
import com.rogoz208.qafpay.data.remote.dto.profile.ProfileDto

interface QafPayRepository {

    suspend fun sessionTest(): AuthDto

    suspend fun sessionOpen(): AuthDto

    suspend fun sessionClose(): AuthDto

    suspend fun otpSend(identifier: String): AuthDto

    suspend fun otpVerify(otp: String): AuthDto

    suspend fun getUserProfile(): ProfileDto
}