package com.sejun2.trippath.data.network.interceptor

import android.content.Context
import com.sejun2.trippath.BuildConfig
import com.sejun2.trippath.data.network.util.ApiConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        val requestWithHeaders = originalRequest.newBuilder()
            .header(ApiConstants.Headers.CONTENT_TYPE, ApiConstants.ContentType.APPLICATION_JSON)
            .header(ApiConstants.Headers.ACCEPT, ApiConstants.ContentType.APPLICATION_JSON)
            .header(ApiConstants.Headers.USER_AGENT, getUserAgent())
            .header("X-App-Version", BuildConfig.VERSION_NAME)
            .header("X-Platform", "Android")
            .build()
        
        return chain.proceed(requestWithHeaders)
    }
    
    private fun getUserAgent(): String {
        return "TripPath Android/${BuildConfig.VERSION_NAME} (${android.os.Build.MODEL}; Android ${android.os.Build.VERSION.RELEASE})"
    }
}