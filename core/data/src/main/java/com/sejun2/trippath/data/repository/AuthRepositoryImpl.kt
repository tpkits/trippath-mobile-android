package com.sejun2.trippath.data.repository

import com.sejun2.trippath.data.auth.SessionManager
import com.sejun2.trippath.data.local.TokenDataStore
import com.sejun2.trippath.data.network.api.AuthApiService
import com.sejun2.trippath.data.network.dto.request.LoginRequest
import com.sejun2.trippath.data.network.dto.response.LoginResponse
import com.sejun2.trippath.domain.model.AuthToken
import com.sejun2.trippath.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val sessionManager: SessionManager,
    private val tokenDataStore: TokenDataStore
) : IAuthRepository {

    override fun login(token: String, providerKey: String): Flow<AuthToken> = flow {
        val response = authApiService.mobileLogin(providerKey, LoginRequest(idToken = token))
        if (response.isSuccessful && response.body() != null) {
            val loginResponse = response.body()!!

            // 토큰 저장
            tokenDataStore.saveTokens(loginResponse.accessToken, loginResponse.refreshToken)

            // 세션 상태 업데이트
            sessionManager.updateLoginState(
                isLoggedIn = true,
                loginResponse = loginResponse
            )

            emit(loginResponse.toAuthToken())
        } else {
            throw Exception("로그인 실패: ${response.code()}")
        }
    }

    override fun refreshToken(refreshToken: String): Flow<AuthToken> = flow {
        val response = authApiService.reissueToken()
        if (response.isSuccessful && response.body() != null) {
            val loginResponse = response.body()!!

            // 새로운 토큰 저장
            tokenDataStore.saveTokens(loginResponse.accessToken, loginResponse.refreshToken)

            // 세션 토큰 업데이트
            sessionManager.updateTokens(loginResponse.accessToken, loginResponse.refreshToken)

            emit(loginResponse.toAuthToken())
        } else {
            throw Exception("토큰 갱신 실패: ${response.code()}")
        }
    }

    override fun getUserInfo(): Flow<String> = flow {
        val response = authApiService.getUserInfo()
        if (response.isSuccessful && response.body() != null) {
            val userInfo = response.body()!!

            // 세션에 사용자 정보 저장
            sessionManager.updateUserInfo(userInfo)

            emit("${userInfo.name} (${userInfo.email})")
        } else {
            throw Exception("사용자 정보 조회 실패: ${response.code()}")
        }
    }

    override suspend fun logout() {
        try {
            val response = authApiService.mobileLogout()

            tokenDataStore.clearTokens()
            sessionManager.logout()

        } catch (e: Exception) {
            tokenDataStore.clearTokens()
            sessionManager.logout()
            throw e
        }
    }

    override suspend fun initializeSession() {
        val accessToken = tokenDataStore.getAccessToken()
        val refreshToken = tokenDataStore.getRefreshToken()

        if (!accessToken.isNullOrEmpty() && !refreshToken.isNullOrEmpty()) {
            sessionManager.updateTokens(accessToken, refreshToken)
            sessionManager.updateLoginState(isLoggedIn = true)
        }
    }
}

private fun LoginResponse.toAuthToken(): AuthToken =
    AuthToken(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
