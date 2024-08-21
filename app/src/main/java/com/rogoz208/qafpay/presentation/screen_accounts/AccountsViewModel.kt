package com.rogoz208.qafpay.presentation.screen_accounts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.domain.use_cases.accounts.AccountsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountsUseCases: AccountsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AccountsState())
    val state: State<AccountsState> = _state

    init {
        getAccounts()
    }

    private fun getAccounts() {
        accountsUseCases.getAccountsUseCase().onEach { result ->
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
                        accounts = result.data ?: listOf()
                    )
                }

                is Resource.Success -> {
                    _state.value = state.value.copy(
                        accounts = result.data ?: listOf(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}