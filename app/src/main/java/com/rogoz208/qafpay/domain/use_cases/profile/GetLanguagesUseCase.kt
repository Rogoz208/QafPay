package com.rogoz208.qafpay.domain.use_cases.profile

import com.rogoz208.qafpay.common.Resource
import com.rogoz208.qafpay.data.remote.dto.languages.toLanguagesList
import com.rogoz208.qafpay.domain.model.Language
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetLanguagesUseCase(
    private val repo: QafPayRepository
) {

    operator fun invoke(): Flow<Resource<List<Language>>> = flow {
        try {
            emit(Resource.Loading())
            val languages = repo.getLanguages().toLanguagesList()
            emit(Resource.Success(languages))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}