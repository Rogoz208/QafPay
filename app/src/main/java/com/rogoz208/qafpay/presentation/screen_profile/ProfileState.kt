package com.rogoz208.qafpay.presentation.screen_profile

import com.rogoz208.qafpay.domain.model.profile.Profile

data class ProfileState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val profile: Profile? = null
)