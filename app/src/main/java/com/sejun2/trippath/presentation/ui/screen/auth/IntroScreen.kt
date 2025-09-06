package com.sejun2.trippath.presentation.ui.screen.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sejun2.trippath.R
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
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
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
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
                .padding(it),
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))
                Image(
                    painter = painterResource(R.drawable.ic_logo_trippath),
                    contentDescription = "image_ic_logo_trippath"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("TRIP PATH", style = MaterialTheme.typography.displayMedium)
                Spacer(modifier = Modifier.weight(1f))
                OauthProvider.entries.toTypedArray().indices.forEach { provider ->
                    SocialLoginButton(
                        provider = OauthProvider.entries[provider],
                        oauthLogin = loginWithOauth
                    )
                }
            }
        }
    }
}

@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    provider: OauthProvider,
    oauthLogin: (OauthProvider) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .fillMaxWidth()
            .background(
                color = provider.backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                enabled = true,
                onClick = {
                    oauthLogin(provider)
                }
            )
            .then(
                if (provider.borderColor != null) {
                    Modifier.border(
                        1.dp,
                        provider.borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                } else {
                    Modifier
                }
            )
            .padding(
                vertical = 12.dp,
                horizontal = 8.dp
            ),
    ) {
        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(start = 16.dp),
            painter = painterResource(getLogoDrawableRes(provider)),
            contentDescription = "${provider.value} 로고"
        )
        Text(
            modifier = Modifier.align(alignment = Alignment.Center),
            text = when (provider) {
                OauthProvider.GOOGLE -> "Google로 계속하기"
                OauthProvider.KAKAO -> "카카오로 계속하기"
            },
            style = TripPathTypography.LabelMedium.copy(
                color = TripPathColors.Neutral10
            )
        )
    }
}

private fun getLogoDrawableRes(provider: OauthProvider): Int {
    return when (provider) {
        OauthProvider.GOOGLE -> R.drawable.ic_logo_google
        OauthProvider.KAKAO -> R.drawable.ic_logo_kakao
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun IntroScreenPreview() {
    TripPathTheme {
        IntroScreen(
            loginWithOauth = {},
            closeIntroWithoutAuthentication = {}
        )
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun IntroScreenDarkPreview() {
    TripPathTheme {
        IntroScreen(
            loginWithOauth = {},
            closeIntroWithoutAuthentication = {}
        )
    }
}