package com.rogoz208.qafpay.domain.use_cases.profile

data class ProfileUseCases(
    val getProfileUseCase: GetProfileUseCase,
    val getAllCountriesUseCase: GetAllCountriesUseCase,
    val getCitiesUseCase: GetCitiesUseCase,
    val getLanguagesUseCase: GetLanguagesUseCase,
    val updateUserProfileUseCase: UpdateUserProfileUseCase
)