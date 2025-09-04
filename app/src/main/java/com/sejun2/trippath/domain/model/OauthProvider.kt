package com.sejun2.trippath.domain.model

import androidx.compose.ui.graphics.Color
import com.sejun2.trippath.presentation.ui.theme.TripPathColors

enum class OauthProvider(val value: String, val backgroundColor: Color, val borderColor: Color? ) {
    GOOGLE("google", Color(0xFFFFFFFF), borderColor = TripPathColors.Neutral10), KAKAO("kakao", Color(0xFFF9E000), null)
}