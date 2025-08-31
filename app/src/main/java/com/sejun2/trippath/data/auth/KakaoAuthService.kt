package com.sejun2.trippath.data.auth

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class KakaoAuthService(private val context: Context) {
    suspend fun loginWithKakaoTalk(): Result<OAuthToken> {
        return withContext(Dispatchers.IO) {
            suspendCoroutine<Result<OAuthToken>> { continuation ->
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    val result = when {
                        error != null -> Result.failure(error)
                        token != null -> Result.success(token)
                        else -> Result.failure(Exception("Unknown error"))
                    }
                    continuation.resume(result)
                }
            }
        }
    }

    suspend fun loginWithKakaoAccount(): Result<OAuthToken> {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                    val result = when {
                        error != null -> Result.failure(error)
                        token != null -> Result.success(token)
                        else -> Result.failure(Exception("Unknown error"))
                    }
                    continuation.resume(result)
                }
            }

        }
    }
}

class KakaoAuthException(message: String, cause: Throwable? = null) : Exception(message, cause)
