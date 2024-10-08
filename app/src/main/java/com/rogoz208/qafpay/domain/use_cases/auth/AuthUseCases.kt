package com.rogoz208.qafpay.domain.use_cases.auth

data class AuthUseCases(
    val sessionTestUseCase: SessionTestUseCase,
    val sessionOpenUseCase: SessionOpenUseCase,
    val sessionCloseUseCase: SessionCloseUseCase,
    val otpSendUseCase: OtpSendUseCase,
    val otpVerifyUseCase: OtpVerifyUseCase
)