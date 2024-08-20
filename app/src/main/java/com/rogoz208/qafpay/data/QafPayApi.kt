package com.rogoz208.qafpay.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rogoz208.qafpay.data.remote.dto.AuthDto
import com.rogoz208.qafpay.data.remote.dto.DataDto
import com.rogoz208.qafpay.data.remote.utils.DataDeserializer
import com.rogoz208.qafpay.data.remote.dto.OtpSendRequest
import com.rogoz208.qafpay.data.remote.dto.OtpVerifyRequest
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
        .registerTypeAdapter(DataDto::class.java, DataDeserializer())
        .create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(modifiedGson))
        .client(modifiedOkHttpClient)
        .build()
        .create(QafPayApi::class.java)
}