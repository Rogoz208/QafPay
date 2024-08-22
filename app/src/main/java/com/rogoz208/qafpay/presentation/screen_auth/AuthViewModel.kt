package com.rogoz208.qafpay.presentation.screen_auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    init {
        sessionOpen()
    }

    private fun sessionOpen() {
        authUseCases.sessionOpenUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = AuthState(
                        errorMessage = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true, auth = result.data)
                }

                is Resource.Success -> {
                    _state.value = AuthState(auth = result.data, isSessionOpened = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateEmail(email: String) {
        _state.value = state.value.copy(email = email)
    }

    fun updateOtp(otp: String) {
        _state.value = state.value.copy(enteredOtp = otp)
    }

    fun otpSend(email: String) {
        authUseCases.otpSendUseCase(email).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = AuthState(
                        errorMessage = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true, auth = result.data)
                }

                is Resource.Success -> {
                    _state.value = AuthState(
                        auth = result.data,
                        isSessionOpened = true,
                        isOtpSent = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun otpVerify(otp: String) {
        authUseCases.otpVerifyUseCase(otp).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = AuthState(
                        errorMessage = result.message ?: "An unexpected error occurred",
                        isOtpSent = true
                        )
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true, auth = result.data)
                }

                is Resource.Success -> {
                    _state.value = AuthState(
                        auth = result.data,
                        isSessionOpened = true,
                        isOtpVerified = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}