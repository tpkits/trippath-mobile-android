package com.sejun2.trippath.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun TripPathTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Dynamic color는 기본적으로 비활성화 (브랜드 컬러 유지)
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Dynamic Color 비활성화
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    DynamicStatusBarController(colorScheme.background)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun DynamicStatusBarController(backgroundColor: Color) {
    val view = LocalView.current
    val isLightBackground = remember(backgroundColor) {
        backgroundColor.luminance() > 0.5f
    }

    SideEffect {
        val window = (view.context as android.app.Activity).window
        val windowInsetsController = WindowCompat.getInsetsController(window, view)

        windowInsetsController.isAppearanceLightStatusBars = isLightBackground
        windowInsetsController.isAppearanceLightNavigationBars = isLightBackground
    }
}