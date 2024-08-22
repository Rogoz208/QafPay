package com.rogoz208.qafpay.domain.use_cases.profile

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.countries.toCountriesList
import com.rogoz208.qafpay.domain.model.Country
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetAllCountriesUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())
            val countries = repo.getAllCountries().toCountriesList()
            emit(Resource.Success(countries))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}