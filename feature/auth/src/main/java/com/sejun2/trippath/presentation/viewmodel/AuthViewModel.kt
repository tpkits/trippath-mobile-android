package com.sejun2.trippath.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejun2.trippath.data.auth.GoogleCredentialService
import com.sejun2.trippath.data.auth.SessionManager
import com.sejun2.trippath.data.auth.KakaoAuthService
import com.sejun2.trippath.data.model.OauthProvider
import com.sejun2.trippath.domain.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
    val sessionManager: SessionManager,
    private val googleCredentialService: GoogleCredentialService,
    private val kakaoAuthService: KakaoAuthService
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.initializeSession()
        }
    }

    fun loginWithOauth(provider: OauthProvider, activityContext: Context) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            when (provider) {
                OauthProvider.GOOGLE -> signInWithGoogle(activityContext)
                OauthProvider.KAKAO -> signInWithKakao()
            }
        }
    }

    private suspend fun signInWithGoogle(activityContext: Context) {
        googleCredentialService.getGoogleIdToken(activityContext)
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "Google 로그인 실패"
                )
            }.onSuccess {
                performLogin(it, OauthProvider.GOOGLE)
            }
    }

    private suspend fun signInWithKakao() {
        kakaoAuthService.loginWithKakaoAccount()
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "Kakao 로그인 실패"
                )
            }.onSuccess {
                performLogin(it.idToken.toString(), OauthProvider.KAKAO)
            }
    }

    private suspend fun performLogin(token: String, provider: OauthProvider) {
        authRepository.login(token, provider.key)
            .catch { exception ->
                Timber.e("로그인 실패: $exception")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "로그인 실패"
                )
            }
            .collect { loginResponse ->
                Timber.d("로그인 성공: $loginResponse")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    success = true,
                )
            }
    }

    fun refreshToken() {
        viewModelScope.launch {
            val currentRefreshToken = sessionManager.getCurrentRefreshToken()
            if (currentRefreshToken != null) {
                authRepository.refreshToken(currentRefreshToken)
                    .catch { exception ->
                        _uiState.value = _uiState.value.copy(
                            error = exception.message ?: "토큰 갱신 실패"
                        )
                    }
                    .collect {
                        // 토큰 갱신 완료, SessionManager가 자동으로 상태 업데이트
                    }
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            authRepository.getUserInfo()
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(
                        error = exception.message ?: "사용자 정보 조회 실패"
                    )
                }
                .collect {
                    // 사용자 정보는 SessionManager에서 관리
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                authRepository.logout()
                _uiState.value = AuthUiState()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "로그아웃 실패"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

@Stable
data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean? = null
)
