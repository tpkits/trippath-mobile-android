package com.sejun2.trippath.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.sejun2.trippath.core.designsystem.R
import com.sejun2.trippath.data.model.OauthProvider
import com.sejun2.trippath.presentation.ui.theme.TripPathColors

data class SocialLoginButtonModel(
    val label: String,
    @DrawableRes val iconRes: Int,
    val backgroundColor: Color,
    val borderColor: Color?
)

fun OauthProvider.toSocialLoginButtonModel(): SocialLoginButtonModel =
    when (this) {
        OauthProvider.GOOGLE -> SocialLoginButtonModel(
            label = "Google로 계속하기",
            iconRes = R.drawable.ic_logo_google,
            backgroundColor = Color(0xFFFFFFFF),
            borderColor = TripPathColors.Neutral10
        )

        OauthProvider.KAKAO -> SocialLoginButtonModel(
            label = "카카오로 계속하기",
            iconRes = R.drawable.ic_logo_kakao,
            backgroundColor = Color(0xFFF9E000),
            borderColor = null
        )
    }
