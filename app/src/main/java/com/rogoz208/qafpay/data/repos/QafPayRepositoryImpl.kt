package com.rogoz208.qafpay.data.repos

import com.rogoz208.qafpay.data.QafPayApi
import com.rogoz208.qafpay.data.remote.dto.AuthDto
import com.rogoz208.qafpay.data.remote.dto.OtpSendRequest
import com.rogoz208.qafpay.data.remote.dto.OtpVerifyRequest
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import javax.inject.Inject

class QafPayRepositoryImpl @Inject constructor(
    private val api: QafPayApi
) : QafPayRepository {

    override suspend fun sessionTest(): AuthDto {
        return api.sessionTest()
    }

    override suspend fun sessionOpen(): AuthDto {
        return api.sessionOpen()
    }

    override suspend fun sessionClose(): AuthDto {
        return api.sessionClose()
    }

    override suspend fun otpSend(identifier: String): AuthDto {
        return api.otpSend(OtpSendRequest(identifier = identifier))
    }

    override suspend fun otpVerify(otp: String): AuthDto {
        return api.otpVerify(OtpVerifyRequest(otp))
    }
}