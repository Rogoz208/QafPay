package com.rogoz208.qafpay.domain.use_cases

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.toAuth
import com.rogoz208.qafpay.domain.model.Auth
import com.rogoz208.qafpay.domain.model.Status
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class OtpVerifyUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(otp: String): Flow<Resource<Auth>> = flow {
        try {
            emit(Resource.Loading())
            val auth = repo.otpVerify(otp).toAuth()
            when (auth.status) {
                Status.SUCCESS -> emit(Resource.Success(auth))
                Status.ERROR -> emit(Resource.Error(auth.userMessage))
            }
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}

