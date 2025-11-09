package com.sejun2.trippath.data.auth

import com.sejun2.trippath.data.network.dto.response.LoginResponse
import com.sejun2.trippath.data.network.dto.response.UserInfoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUser = MutableStateFlow<UserInfoResponse?>(null)
    val currentUser: StateFlow<UserInfoResponse?> = _currentUser.asStateFlow()

    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> = _accessToken.asStateFlow()

    private val _refreshToken = MutableStateFlow<String?>(null)
    val refreshToken: StateFlow<String?> = _refreshToken.asStateFlow()

    fun updateLoginState(
        isLoggedIn: Boolean,
        loginResponse: LoginResponse? = null,
        userInfo: UserInfoResponse? = null
    ) {
        _isLoggedIn.value = isLoggedIn

        if (isLoggedIn && loginResponse != null) {
            _accessToken.value = loginResponse.accessToken
            _refreshToken.value = loginResponse.refreshToken
        }

        if (userInfo != null) {
            _currentUser.value = userInfo
        }
    }

    fun updateTokens(accessToken: String, refreshToken: String) {
        _accessToken.value = accessToken
        _refreshToken.value = refreshToken
    }

    fun updateUserInfo(userInfo: UserInfoResponse) {
        _currentUser.value = userInfo
    }

    fun logout() {
        _isLoggedIn.value = false
        _currentUser.value = null
        _accessToken.value = null
        _refreshToken.value = null
    }

    fun getCurrentAccessToken(): String? = _accessToken.value
    fun getCurrentRefreshToken(): String? = _refreshToken.value
}
