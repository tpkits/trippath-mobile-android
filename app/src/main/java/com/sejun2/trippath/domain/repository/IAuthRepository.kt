package com.sejun2.trippath.domain.repository

import android.content.Context
import com.sejun2.trippath.data.network.dto.response.LoginResponse
import com.sejun2.trippath.domain.model.OauthProvider
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    // Oauth Provider 으로 로그인
    // providers: "google", "kakao", "apple"
    fun loginWithOauth(provider: OauthProvider): Flow<String>

    // 로그인
    fun login(token: String, provider: OauthProvider): Flow<LoginResponse>

    // 토큰 리프레시
    fun refreshToken(refreshToken: String): Flow<LoginResponse>

    fun getUserInfo(): Flow<String>

    suspend fun logout()

    suspend fun initializeSession()
}