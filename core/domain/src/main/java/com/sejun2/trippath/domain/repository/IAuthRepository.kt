package com.sejun2.trippath.domain.repository

import com.sejun2.trippath.domain.model.AuthToken
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    // 로그인
    fun login(token: String, providerKey: String): Flow<AuthToken>

    // 토큰 리프레시
    fun refreshToken(refreshToken: String): Flow<AuthToken>

    fun getUserInfo(): Flow<String>

    suspend fun logout()

    suspend fun initializeSession()
}
