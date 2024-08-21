package com.rogoz208.qafpay.presentation.screen_profile

import com.rogoz208.qafpay.domain.model.City
import com.rogoz208.qafpay.domain.model.Country
import com.rogoz208.qafpay.domain.model.Language
import com.rogoz208.qafpay.domain.model.Profile

data class ProfileState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val profile: Profile? = null,
    val countries: List<Country> = listOf(),
    val cities: List<City> = listOf(),
    val languages: List<Language> = listOf(),
    val isChanged: Boolean = false
)