package com.sejun2.trippath.data.network.interceptor

import com.sejun2.trippath.data.network.util.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    // TODO: TokenManager 주입 예정
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // 인증이 필요하지 않은 엔드포인트는 그대로 진행
        // TODO

        // TODO: TokenManager에서 액세스 토큰 가져오기
        val accessToken = getAccessToken()
        
        val authenticatedRequest = originalRequest.newBuilder()
            .header(ApiConstants.Headers.AUTHORIZATION, "Bearer $accessToken")
            .build()
        
        val response = chain.proceed(authenticatedRequest)
        
        // 401 에러 시 토큰 갱신 로직
        if (response.code == ApiConstants.HttpStatusCode.UNAUTHORIZED) {
            response.close()
            
            // TODO: 토큰 갱신 로직 구현
            val newAccessToken = refreshToken()
            
            if (newAccessToken != null) {
                val newRequest = originalRequest.newBuilder()
                    .header(ApiConstants.Headers.AUTHORIZATION, "Bearer $newAccessToken")
                    .build()
                
                return chain.proceed(newRequest)
            }
        }
        
        return response
    }
    
    private fun getAccessToken(): String? {
        // TODO: SharedPreferences나 DataStore에서 토큰 가져오기
        return null
    }
    
    private fun refreshToken(): String? {
        // TODO: 토큰 갱신 API 호출
        return null
    }
}