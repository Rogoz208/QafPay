package com.rogoz208.qafpay.domain.repos

import com.rogoz208.qafpay.data.remote.dto.accounts.AccountsDto
import com.rogoz208.qafpay.data.remote.dto.auth.AuthDto
import com.rogoz208.qafpay.data.remote.dto.cities.CitiesDto
import com.rogoz208.qafpay.data.remote.dto.countries.CountriesDto
import com.rogoz208.qafpay.data.remote.dto.languages.LanguagesDto
import com.rogoz208.qafpay.data.remote.dto.profile.ProfileDto
import com.rogoz208.qafpay.data.remote.dto.transactions.TransactionsDto

interface QafPayRepository {

    suspend fun sessionTest(): AuthDto

    suspend fun sessionOpen(): AuthDto

    suspend fun sessionClose(): AuthDto

    suspend fun otpSend(identifier: String): AuthDto

    suspend fun otpVerify(otp: String): AuthDto

    suspend fun getUserProfile(): ProfileDto

    suspend fun getAllCountries(): CountriesDto

    suspend fun getCities(countryId: String): CitiesDto

    suspend fun getLanguages(): LanguagesDto

    suspend fun updateUserProfile(
        cityId: String,
        countryId: String,
        identifier: String,
        language: String,
        name: String
    ): ProfileDto

    suspend fun getAccounts(): AccountsDto

    suspend fun getTransactions(accountId: String): TransactionsDto
}