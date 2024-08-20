package com.rogoz208.qafpay.domain.repos

import com.rogoz208.qafpay.data.remote.dto.AuthDto

interface QafPayRepository {

    suspend fun sessionTest(): AuthDto

    suspend fun sessionOpen(): AuthDto

    suspend fun sessionClose(): AuthDto

    suspend fun otpSend(identifier: String): AuthDto

    suspend fun otpVerify(otp: String): AuthDto
}