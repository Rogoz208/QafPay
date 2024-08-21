package com.rogoz208.qafpay.domain.use_cases.accounts

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.accounts.toAccountsList
import com.rogoz208.qafpay.domain.model.Account
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetAccountsUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(): Flow<Resource<List<Account>>> = flow {
        try {
            emit(Resource.Loading())
            val accounts = repo.getAccounts().toAccountsList()
            emit(Resource.Success(accounts))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}