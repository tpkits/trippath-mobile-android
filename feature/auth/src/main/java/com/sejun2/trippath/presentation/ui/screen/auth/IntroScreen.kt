package com.sejun2.trippath.presentation.ui.screen.auth

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sejun2.trippath.core.designsystem.R as CoreResource
import com.sejun2.trippath.data.model.OauthProvider
import com.sejun2.trippath.presentation.ui.component.SocialLoginButton
import com.sejun2.trippath.presentation.ui.model.toSocialLoginButtonModel
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTheme
import com.sejun2.trippath.presentation.ui.theme.TripPathTypography
import com.sejun2.trippath.presentation.util.circleClickable
import com.sejun2.trippath.presentation.viewmodel.AuthViewModel

@Composable
fun IntroRoute(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
    closeIntroWithoutAuthentication: () -> Unit,
    onAuthSuccess: () -> Unit
) {
    LaunchedEffect(Unit) {
        authViewModel.uiState.collect { authUiState ->
            if (authUiState.success == true) {
                onAuthSuccess()
            }
        }
    }

    IntroScreen(
        modifier = modifier,
        loginWithOauth = authViewModel::loginWithOauth,
        closeIntroWithoutAuthentication = closeIntroWithoutAuthentication,
    )
}

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    loginWithOauth: (OauthProvider, Context) -> Unit,
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
                        .clip(shape = CircleShape)
                        .circleClickable(
                            true, onClick = closeIntroWithoutAuthentication,
                        )
                        .padding(8.dp),
                    text = stringResource(com.sejun2.trippath.feature.auth.R.string.auth_skip),
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
                    painter = painterResource(CoreResource.drawable.ic_logo_trippath),
                    contentDescription = "image_ic_logo_trippath"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("TRIP PATH", style = MaterialTheme.typography.displayMedium)
                Spacer(modifier = Modifier.weight(1f))
                val context = LocalContext.current
                OauthProvider.entries.toTypedArray().forEach { provider ->
                    val uiModel = provider.toSocialLoginButtonModel()
                    SocialLoginButton(
                        label = uiModel.label,
                        backgroundColor = uiModel.backgroundColor,
                        borderColor = uiModel.borderColor,
                        iconRes = uiModel.iconRes,
                        onClick = { loginWithOauth(provider, context) }
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun IntroScreenPreview() {
    TripPathTheme {
        IntroScreen(
            loginWithOauth = { _, _ -> },
            closeIntroWithoutAuthentication = {}
        )
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun IntroScreenDarkPreview() {
    TripPathTheme {
        IntroScreen(
            loginWithOauth = { _, _ -> },
            closeIntroWithoutAuthentication = {}
        )
    }
}
