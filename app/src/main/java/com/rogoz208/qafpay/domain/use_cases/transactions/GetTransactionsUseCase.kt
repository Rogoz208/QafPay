package com.rogoz208.qafpay.domain.use_cases.transactions

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.transactions.toTransactionsList
import com.rogoz208.qafpay.domain.model.Transaction
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetTransactionsUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(accountId: String): Flow<Resource<List<Transaction>>> = flow {
        try {
            emit(Resource.Loading())
            val transactions = repo.getTransactions(accountId).toTransactionsList()
            emit(Resource.Success(transactions))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}