package com.rogoz208.qafpay.presentation.screen_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.domain.use_cases.profile.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        getProfile()
    }

    private fun getProfile() {
        profileUseCases.getProfile().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = ProfileState(
                        errorMessage = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = ProfileState(isLoading = true, profile = result.data)
                }

                is Resource.Success -> {
                    _state.value = ProfileState(profile = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}