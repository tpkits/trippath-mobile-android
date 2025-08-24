package com.sejun2.trippath.presentation.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejun2.trippath.data.repository.AuthRepositoryImpl
import com.sejun2.trippath.domain.auth.SessionManager
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.domain.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
    private val authRepositoryImpl: AuthRepositoryImpl,
    val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        // 앱 시작시 저장된 토큰으로 세션 초기화
        viewModelScope.launch {
            authRepositoryImpl.initializeSession()
        }
    }

    fun loginWithOauth(provider: OauthProvider) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            authRepository.loginWithOauth(provider)
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "OAuth 로그인 실패"
                    )
                }
                .collect { oauthToken ->
                    performLogin(oauthToken)
                }
        }
    }

    private suspend fun performLogin(token: String) {
        authRepository.login(token)
            .catch { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "로그인 실패"
                )
            }
            .collect { loginResponse ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false
                )
                // 로그인 상태는 SessionManager에서 관리
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
                authRepositoryImpl.logout()
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
    val error: String? = null
)