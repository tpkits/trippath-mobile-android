package com.sejun2.trippath.data.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleCredentialService @Inject constructor(
) {

    suspend fun getGoogleIdToken(activityContext: Context): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
//                val googleIdOption = GetGoogleIdOption.Builder()
//                    .setFilterByAuthorizedAccounts(false)
//                    .setServerClientId(getServerClientId())
//                    .build()

                val signInWithGoogleOption: GetSignInWithGoogleOption =
                    GetSignInWithGoogleOption.Builder(
                        serverClientId = getServerClientId()
                    ).build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(signInWithGoogleOption)
                    .build()

                val credentialManager = CredentialManager.create(activityContext)
                val result = credentialManager.getCredential(
                    request = request,
                    context = activityContext
                )

                val credential = GoogleIdTokenCredential.createFrom(result.credential.data)
                Result.success(credential.idToken)
            } catch (e: GetCredentialException) {
                Timber.e("Google 인증 실패: ${e.message}")
                Result.failure(GoogleAuthException("Google 인증 실패: ${e.message}", e))
            } catch (e: GoogleIdTokenParsingException) {
                Timber.e("토큰 파싱 실패: ${e.message}")
                Result.failure(GoogleAuthException("토큰 파싱 실패: ${e.message}", e))
            } catch (e: Exception) {
                Timber.e("알 수 없는 오류: ${e.message}")
                Result.failure(GoogleAuthException("알 수 없는 오류: ${e.message}", e))
            }
        }
    }

    private fun getServerClientId(): String {
        // TODO: 별도 환경변수로 분리
        return "701051835178-ga1hs198ck3vjt89q69humqsgi4j7ic8.apps.googleusercontent.com"
    }
}

class GoogleAuthException(message: String, cause: Throwable? = null) : Exception(message, cause)