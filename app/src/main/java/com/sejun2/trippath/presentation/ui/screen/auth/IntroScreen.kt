package com.sejun2.trippath.presentation.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTypography
import com.sejun2.trippath.presentation.viewmodel.AuthViewModel

@Composable
fun IntroRoute(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel<AuthViewModel>(),
) {
    IntroScreen(
        modifier,
        authViewModel::loginWithOauth,
        authViewModel::logout,// TODO: 건너뛰기 기능 구현 필요
    )

}

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    loginWithOauth: (OauthProvider) -> Unit,
    closeIntroWithoutAuthentication: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(TripPathColors.BackgroundWhite),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(
                            true, onClick = closeIntroWithoutAuthentication
                        ),
                    text = "건너뛰기",
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(TripPathColors.BackgroundWhite)
                .padding(it),
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text("Logo")
                Spacer(modifier = Modifier.height(16.dp))
                Text("Trippath 슬로건 입니다")
                Spacer(modifier = Modifier.weight(1f))
                OauthProvider.entries.toTypedArray().indices.forEach {
                    SocialLoginButton(
                        provider = OauthProvider.entries[it],
                        onClick = loginWithOauth
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    provider: OauthProvider,
    onClick: (OauthProvider) -> Unit,
) {
    TextButton(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .fillMaxWidth()
            .background(
                color = TripPathColors.BackgroundWhite,
            ),
        onClick = {
            onClick(provider)
        }
    ) {
        Text(
            text = when (provider) {
                OauthProvider.GOOGLE -> "구글 로그인"
                OauthProvider.KAKAO -> "카카오 로그인"
                OauthProvider.APPLE -> "애플 로그인"
            },
            style = TripPathTypography.LabelMedium
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(
        loginWithOauth = {},
        closeIntroWithoutAuthentication = {}
    )
}

