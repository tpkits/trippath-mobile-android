package com.sejun2.trippath.data.repository

import android.util.Log
import com.sejun2.trippath.data.auth.GoogleCredentialService
import com.sejun2.trippath.data.auth.KakaoAuthService
import com.sejun2.trippath.data.local.TokenDataStore
import com.sejun2.trippath.data.network.api.AuthApiService
import com.sejun2.trippath.data.network.dto.request.LoginRequest
import com.sejun2.trippath.data.network.dto.response.LoginResponse
import com.sejun2.trippath.domain.auth.SessionManager
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val googleCredentialService: GoogleCredentialService,
    private val kakaoAuthService: KakaoAuthService,
    private val sessionManager: SessionManager,
    private val tokenDataStore: TokenDataStore
) : IAuthRepository {

    override fun loginWithOauth(provider: OauthProvider): Flow<String> = flow {
        when (provider) {
            OauthProvider.GOOGLE -> {
                val result = googleCredentialService.getGoogleIdToken()
                result.fold(
                    onSuccess = { idToken -> emit(idToken) },
                    onFailure = { exception -> throw exception }
                )
            }

            OauthProvider.KAKAO -> {
                val result = kakaoAuthService.loginWithKakaoAccount()
                result.fold(
                    onSuccess = { token -> emit(token.idToken.toString()) },
                    onFailure = { exception -> throw exception }
                )
            }

            OauthProvider.APPLE -> {
                throw NotImplementedError("Apple 로그인이 아직 구현되지 않았습니다.")
            }
        }
    }

    override fun login(token: String, provider: OauthProvider): Flow<LoginResponse> = flow {
        val response = authApiService.mobileLogin(provider.value, LoginRequest(idToken = token))
        if (response.isSuccessful && response.body() != null) {
            val loginResponse = response.body()!!

            // 토큰 저장
            tokenDataStore.saveTokens(loginResponse.accessToken, loginResponse.refreshToken)

            // 세션 상태 업데이트
            sessionManager.updateLoginState(
                isLoggedIn = true,
                loginResponse = loginResponse
            )

            emit(loginResponse)
        } else {
            throw Exception("로그인 실패: ${response.code()}")
        }
    }

    override fun refreshToken(refreshToken: String): Flow<LoginResponse> = flow {
        val response = authApiService.reissueToken()
        if (response.isSuccessful && response.body() != null) {
            val loginResponse = response.body()!!

            // 새로운 토큰 저장
            tokenDataStore.saveTokens(loginResponse.accessToken, loginResponse.refreshToken)

            // 세션 토큰 업데이트
            sessionManager.updateTokens(loginResponse.accessToken, loginResponse.refreshToken)

            emit(loginResponse)
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
            // API 로그아웃 호출
            val response = authApiService.mobileLogout()

            // 성공 여부와 관계없이 로컬 데이터 정리
            tokenDataStore.clearTokens()
            sessionManager.logout()

        } catch (e: Exception) {
            // 네트워크 오류여도 로컬 데이터는 정리
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