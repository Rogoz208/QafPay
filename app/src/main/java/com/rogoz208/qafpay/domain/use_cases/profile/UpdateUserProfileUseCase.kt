package com.rogoz208.qafpay.domain.use_cases.profile

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.profile.toProfile
import com.rogoz208.qafpay.domain.model.Profile
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UpdateUserProfileUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(
        cityId: String,
        countryId: String,
        identifier: String,
        language: String,
        name: String
    ): Flow<Resource<Profile>> = flow {
        try {
            emit(Resource.Loading())
            val profile =
                repo.updateUserProfile(
                    cityId = cityId,
                    countryId = countryId,
                    identifier = identifier,
                    language = language,
                    name = name
                ).toProfile()
            emit(Resource.Success(profile))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}