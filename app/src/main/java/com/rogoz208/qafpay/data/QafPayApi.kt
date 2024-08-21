package com.rogoz208.qafpay.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rogoz208.qafpay.data.remote.dto.countries.GetAllCountriesRequest
import com.rogoz208.qafpay.data.remote.dto.auth.AuthDataDto
import com.rogoz208.qafpay.data.remote.dto.auth.AuthDto
import com.rogoz208.qafpay.data.remote.dto.auth.OtpSendRequest
import com.rogoz208.qafpay.data.remote.dto.auth.OtpVerifyRequest
import com.rogoz208.qafpay.data.remote.dto.cities.CitiesDto
import com.rogoz208.qafpay.data.remote.dto.cities.GetCitiesRequest
import com.rogoz208.qafpay.data.remote.dto.countries.CountriesDto
import com.rogoz208.qafpay.data.remote.dto.languages.LanguagesDto
import com.rogoz208.qafpay.data.remote.dto.profile.ProfileDto
import com.rogoz208.qafpay.data.remote.dto.profile.UpdateUserProfileRequest
import com.rogoz208.qafpay.data.remote.utils.DataDeserializer
import com.rogoz208.qafpay.data.remote.utils.QafPayCookieJar
import com.rogoz208.qafpay.data.remote.utils.QafPayHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface QafPayApi {

    @POST("/api/v1/auth/session/test")
    suspend fun sessionTest(): AuthDto

    @POST("/api/v1/auth/session/open")
    suspend fun sessionOpen(): AuthDto

    @POST("/api/v1/auth/session/close")
    suspend fun sessionClose(): AuthDto

    @POST("/api/v1/auth/otp/send")
    suspend fun otpSend(@Body request: OtpSendRequest): AuthDto

    @POST("/api/v1/auth/otp/verify")
    suspend fun otpVerify(@Body request: OtpVerifyRequest): AuthDto

    @POST("/api/v1/users/get")
    suspend fun getUserProfile(): ProfileDto

    @POST("/api/v1/geo/countries")
    suspend fun getAllCountries(
        @Body getAllCountriesRequest: GetAllCountriesRequest
    ): CountriesDto

    @POST("/api/v1/geo/cities")
    suspend fun getCities(@Body getCitiesRequest: GetCitiesRequest): CitiesDto

    @POST("/api/v1/translations/get")
    suspend fun getLanguages(): LanguagesDto

    @POST("/api/v1/users/update")
    suspend fun updateUserProfile(
        @Body updateUserProfileRequest: UpdateUserProfileRequest
    ): ProfileDto
}

fun QafPayApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    gson: Gson? = null
): QafPayApi {

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .cookieJar(QafPayCookieJar())
        .addInterceptor(QafPayHeaderInterceptor())
        .build()

    val modifiedGson: Gson = (gson?.newBuilder() ?: GsonBuilder())
        .registerTypeAdapter(AuthDataDto::class.java, DataDeserializer())
        .create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(modifiedGson))
        .client(modifiedOkHttpClient)
        .build()
        .create(QafPayApi::class.java)
}