package com.sejun2.trippath.presentation.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.R
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.presentation.ui.theme.TripPathColors
import com.sejun2.trippath.presentation.ui.theme.TripPathTypography

@Composable
fun SocialLoginButton(
    modifier: Modifier = Modifier,
    provider: OauthProvider,
    oauthLogin: (OauthProvider, Context) -> Unit,
) {
    val context = LocalContext.current

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
                    oauthLogin(provider, context)
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
