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
        getCountries()
        getLanguages()
    }

    private fun getProfile() {
        profileUseCases.getProfileUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        errorMessage = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true, profile = result.data)
                }

                is Resource.Success -> {
                    getCities(result.data?.countryId)
                    _state.value = state.value.copy(
                        profile = result.data,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCountries() {
        profileUseCases.getAllCountriesUseCase().onEach { result ->
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
                        countries = result.data ?: listOf()
                    )
                }

                is Resource.Success -> {
                    _state.value =
                        state.value.copy(
                            countries = result.data ?: listOf(),
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCities(countryId: String?) {
        profileUseCases.getCitiesUseCase(countryId).onEach { result ->
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
                        cities = result.data ?: listOf()
                    )
                }

                is Resource.Success -> {
                    _state.value =
                        state.value.copy(
                            cities = result.data ?: listOf(),
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getLanguages() {
        profileUseCases.getLanguagesUseCase().onEach { result ->
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
                        languages = result.data ?: listOf()
                    )
                }

                is Resource.Success -> {
                    _state.value =
                        state.value.copy(
                            languages = result.data ?: listOf(),
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun findCountryIdInList(country: String): String? {
        return state.value.countries.find { countryItem ->
            countryItem.name == country
        }?.id
    }

    fun onSaveChanges() {
        // TODO: Не работает. Причину написал в телеграме.

//        val profile = state.value.profile
//        profileUseCases.updateUserProfileUseCase(
//            cityId = profile?.cityId ?: "",
//            countryId = profile?.countryId ?: "",
//            identifier = profile?.email ?: "",
//            language = profile?.language ?: "",
//            name = profile?.name ?: ""
//        ).onEach { result ->
//            when (result) {
//                is Resource.Error -> {
//                    _state.value = state.value.copy(
//                        errorMessage = result.message ?: "An unexpected error occurred",
//                        isLoading = false
//                    )
//                }
//
//                is Resource.Loading -> {
//                    _state.value = state.value.copy(
//                        isLoading = true,
//                        profile = result.data
//                    )
//                }
//
//                is Resource.Success -> {
//                    _state.value =
//                        state.value.copy(
//                            profile = result.data,
//                            isLoading = false
//                        )
//                }
//            }
//        }.launchIn(viewModelScope)
    }

    fun onNameUpdate(name: String) {
        val profileCopy = state.value.profile?.copy(name = name)
        _state.value = state.value.copy(profile = profileCopy, isChanged = true)
    }

    fun onEmailUpdate(email: String) {
        val profileCopy = state.value.profile?.copy(email = email)
        _state.value = state.value.copy(profile = profileCopy, isChanged = true)
    }

    fun onCountryUpdate(country: String) {
        val countryId = findCountryIdInList(country)
        getCities(countryId)

        val profileCopy = state.value.profile?.copy(country = country)
        _state.value = state.value.copy(profile = profileCopy, isChanged = true)
    }

    fun onCityUpdate(city: String) {
        val profileCopy = state.value.profile?.copy(city = city)
        _state.value = state.value.copy(profile = profileCopy, isChanged = true)
    }

    fun onLanguageUpdate(language: String) {
        val profileCopy = state.value.profile?.copy(language = language)
        _state.value = state.value.copy(profile = profileCopy, isChanged = true)
    }
}