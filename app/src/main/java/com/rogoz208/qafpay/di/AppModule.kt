package com.rogoz208.qafpay.di

import com.rogoz208.qafpay.BuildConfig
import com.rogoz208.qafpay.data.QafPayApi
import com.rogoz208.qafpay.data.repos.QafPayRepositoryImpl
import com.rogoz208.qafpay.domain.repos.QafPayRepository
import com.rogoz208.qafpay.domain.use_cases.auth.AuthUseCases
import com.rogoz208.qafpay.domain.use_cases.auth.OtpSendUseCase
import com.rogoz208.qafpay.domain.use_cases.auth.OtpVerifyUseCase
import com.rogoz208.qafpay.domain.use_cases.auth.SessionCloseUseCase
import com.rogoz208.qafpay.domain.use_cases.auth.SessionOpenUseCase
import com.rogoz208.qafpay.domain.use_cases.auth.SessionTestUseCase
import com.rogoz208.qafpay.domain.use_cases.profile.GetAllCountriesUseCase
import com.rogoz208.qafpay.domain.use_cases.profile.GetCitiesUseCase
import com.rogoz208.qafpay.domain.use_cases.profile.GetLanguagesUseCase
import com.rogoz208.qafpay.domain.use_cases.profile.GetProfileUseCase
import com.rogoz208.qafpay.domain.use_cases.profile.ProfileUseCases
import com.rogoz208.qafpay.domain.use_cases.profile.UpdateUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient? = if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    } else {
        null
    }

    @Provides
    @Singleton
    fun provideQafPayApi(okHttpClient: OkHttpClient?): QafPayApi = QafPayApi(
        baseUrl = BuildConfig.QAF_PAY_API_BASE_URL,
        okHttpClient = okHttpClient
    )

    @Provides
    @Singleton
    fun provideAuthRepository(api: QafPayApi): QafPayRepository {
        return QafPayRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repo: QafPayRepository): AuthUseCases {
        return AuthUseCases(
            SessionTestUseCase(repo),
            SessionOpenUseCase(repo),
            SessionCloseUseCase(repo),
            OtpSendUseCase(repo),
            OtpVerifyUseCase(repo)
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repo: QafPayRepository): ProfileUseCases {
        return ProfileUseCases(
            GetProfileUseCase(repo),
            GetAllCountriesUseCase(repo),
            GetCitiesUseCase(repo),
            GetLanguagesUseCase(repo),
            UpdateUserProfileUseCase(repo)
        )
    }
}