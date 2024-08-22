package com.rogoz208.qafpay.data.repos

import com.rogoz208.qafpay.data.QafPayApi
import com.rogoz208.qafpay.data.remote.dto.accounts.AccountsDto
import com.rogoz208.qafpay.data.remote.dto.countries.GetAllCountriesRequest
import com.rogoz208.qafpay.data.remote.dto.auth.AuthDto
import com.rogoz208.qafpay.data.remote.dto.auth.OtpSendRequest
import com.rogoz208.qafpay.data.remote.dto.auth.OtpVerifyRequest
import com.rogoz208.qafpay.data.remote.dto.cities.CitiesDto
import com.rogoz208.qafpay.data.remote.dto.cities.GetCitiesRequest
import com.rogoz208.qafpay.data.remote.dto.countries.CountriesDto
import com.rogoz208.qafpay.data.remote.dto.languages.LanguagesDto
import com.rogoz208.qafpay.data.remote.dto.profile.ProfileDto
import com.rogoz208.qafpay.data.remote.dto.profile.UpdateUserProfileRequest
import com.rogoz208.qafpay.data.remote.dto.transactions.GetTransactionsRequest
import com.rogoz208.qafpay.data.remote.dto.transactions.TransactionsDto
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import javax.inject.Inject

class QafPayRepositoryImpl @Inject constructor(
    private val api: QafPayApi
) : QafPayRepository {

    override suspend fun sessionTest(): AuthDto {
        return api.sessionTest()
    }

    override suspend fun sessionOpen(): AuthDto {
        return api.sessionOpen()
    }

    override suspend fun sessionClose(): AuthDto {
        return api.sessionClose()
    }

    override suspend fun otpSend(identifier: String): AuthDto {
        return api.otpSend(OtpSendRequest(identifier = identifier))
    }

    override suspend fun otpVerify(otp: String): AuthDto {
        return api.otpVerify(OtpVerifyRequest(otp))
    }

    override suspend fun getUserProfile(): ProfileDto {
        return api.getUserProfile()
    }

    override suspend fun getAllCountries(): CountriesDto {
        return api.getAllCountries(GetAllCountriesRequest())
    }

    override suspend fun getCities(countryId: String): CitiesDto {
        return api.getCities(GetCitiesRequest(countryId = countryId))
    }

    override suspend fun getLanguages(): LanguagesDto {
        return api.getLanguages()
    }

    override suspend fun updateUserProfile(
        cityId: String,
        countryId: String,
        identifier: String,
        language: String,
        name: String
    ): ProfileDto {
        return api.updateUserProfile(
            UpdateUserProfileRequest(
                cityId = cityId,
                countryId = countryId,
                identifier = identifier,
                language = language,
                name = name
            )
        )
    }

    override suspend fun getAccounts(): AccountsDto {
        return api.getAccounts()
    }

    override suspend fun getTransactions(accountId: String): TransactionsDto {
        return api.getTransactions(GetTransactionsRequest(accountId))
    }
}