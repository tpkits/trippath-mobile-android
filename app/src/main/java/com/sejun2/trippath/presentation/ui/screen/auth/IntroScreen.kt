package com.sejun2.trippath.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.presentation.viewmodel.AuthViewModel

@Composable
fun IntroRoute(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel<AuthViewModel>(),
) {
    IntroScreen(
        modifier,
        authViewModel::loginWithOauth
    )

}

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    loginWithOauth: (OauthProvider) -> Unit,
) {
    Surface {
        // Sample composable
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(
                onClick = {
                    loginWithOauth(OauthProvider.GOOGLE)
                }
            ) {
                Text("구글 로그인")
            }
            TextButton(
                onClick = {
                    loginWithOauth(OauthProvider.KAKAO)
                }
            ) {
                Text("카카오 로그인")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(
        loginWithOauth = {}
    )
}

