package com.rogoz208.qafpay.domain.use_cases.profile

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.cities.toCitiesList
import com.rogoz208.qafpay.domain.model.City
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCitiesUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(countryId: String?): Flow<Resource<List<City>>> = flow {
        try {
            emit(Resource.Loading())
            val cities = repo.getCities(countryId ?: "").toCitiesList()
            emit(Resource.Success(cities))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}