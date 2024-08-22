package com.rogoz208.qafpay.presentation.screen_transactions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.domain.use_cases.transactions.TransactionsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val transactionsUseCases: TransactionsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TransactionsState())
    val state: State<TransactionsState> = _state

    private fun getTransactions(accountId: String) {
        transactionsUseCases.getTransactionsUseCase(accountId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        errorMessage = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        transactions = result.data ?: listOf()
                    )
                }

                is Resource.Success -> {
                    _state.value = state.value.copy(
                        transactions = result.data ?: listOf(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onGettingAccountId(accountId: String) {
        getTransactions(accountId)
    }
}