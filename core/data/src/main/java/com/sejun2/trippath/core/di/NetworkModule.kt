package com.sejun2.trippath.core.di

import android.content.Context
import com.sejun2.trippath.data.auth.GoogleCredentialService
import com.sejun2.trippath.data.auth.KakaoAuthService
import com.sejun2.trippath.data.local.TokenDataStore
import com.sejun2.trippath.data.network.api.AuthApiService
import com.sejun2.trippath.data.network.client.NetworkClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(
        networkClient: NetworkClient
    ): AuthApiService {
        return networkClient.createApiService<AuthApiService>()
    }

    @Provides
    @Singleton
    fun provideGoogleCredentialService(
    ): GoogleCredentialService {
        return GoogleCredentialService()
    }

    @Provides
    @Singleton
    fun provideTokenDataStore(
        @ApplicationContext context: Context
    ): TokenDataStore {
        return TokenDataStore(context)
    }

    @Provides
    @Singleton
    fun provideKakaoAuthService(
        @ApplicationContext context: Context
    ): KakaoAuthService {
        return KakaoAuthService(context)
    }
}


