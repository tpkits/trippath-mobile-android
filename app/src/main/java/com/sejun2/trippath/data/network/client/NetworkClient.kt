package com.sejun2.trippath.data.network.client

import com.sejun2.trippath.BuildConfig
import com.sejun2.trippath.data.network.interceptor.AuthInterceptor
import com.sejun2.trippath.data.network.interceptor.HeaderInterceptor
import com.sejun2.trippath.data.network.util.ApiConstants
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkClient @Inject constructor(
    private val headerInterceptor: HeaderInterceptor,
    private val authInterceptor: AuthInterceptor,
    private val moshi: Moshi
) {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(ApiConstants.Timeout.CONNECT, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.Timeout.READ, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.Timeout.WRITE, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
//            .addInterceptor(authInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(createLoggingInterceptor())
                }
            }
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun getBaseUrl(): String {
        return ApiConstants.BASE_URL

        // TODO: 환경별 BaseUrl 분리
    }

    inline fun <reified T> createApiService(): T {
        return retrofit.create(T::class.java)
    }
}