package com.sejun2.trippath.presentation.ui.theme

import androidx.compose.ui.graphics.Color

object TripPathColors {
    
    // Common Colors
    val Common100 = Color(0xFF000000)
    val Common0 = Color(0xFFFFFFFF)
    
    // Neutral Colors
    val Neutral99 = Color(0xFFF8F8F8)
    val Neutral95 = Color(0xFFEFEFEF)
    val Neutral90 = Color(0xFFE0E0E0)
    val Neutral80 = Color(0xFFC2C2C2)
    val Neutral70 = Color(0xFFA3A3A3)
    val Neutral60 = Color(0xFF858585)
    val Neutral50 = Color(0xFF666666)
    val Neutral40 = Color(0xFF474747)
    val Neutral30 = Color(0xFF292929)
    val Neutral20 = Color(0xFF1F1F1F)
    val Neutral10 = Color(0xFF0A0A0A)
    val Neutral0 = Color(0xFF000000)
    
    // Brand Colors (Green)
    val BrandDefault = Color(0xFF1B7B3A)
    val BrandSubtle = Color(0xFF4A9B5E)
    val BrandSubtlest = Color(0xFFC8E6D0)
    val BrandBold = Color(0xFF155D2B)
    
    // Accent Colors
    val Lime = Color(0xFF84E83A)
    val Purple = Color(0xFF9B3AE8)
    val Violet = Color(0xFF5F3AE8)
    val LightBlue = Color(0xFF3A9BE8)
    val Yellow = Color(0xFFE8D73A)
    val Orange = Color(0xFFE8743A)
    val RedOrange = Color(0xFFE83A3A)
    
    // System Colors
    val SystemDanger = Color(0xFFDC3545)
    val SystemSuccess = Color(0xFF28A745)
    val SystemWarning = Color(0xFFFFC107)
    val SystemInformation = Color(0xFF007BFF)
    
    // Semantic Colors
    // Text
    val TextDefault = Color(0xFF292929)
    val TextSubtle = Color(0xFF666666)
    val TextSubtler = Color(0xFFA3A3A3)
    val TextSubtlest = Color(0xFFC2C2C2)
    
    // Border
    val BorderDefault = Color(0xFFE0E0E0)
    val BorderBold = Color(0xFFA3A3A3)
    val BorderInverse = Color(0xFF292929)
    
    // Background
    val BackgroundWhite = Color(0xFFFFFFFF)
    val BackgroundNeutral = Color(0xFFF8F8F8)
    
    // Inverse
    val InverseCommon100 = Color(0xFF000000)
    val InverseCommon0 = Color(0xFFFFFFFF)
}

// Material 3 Color Scheme mappings
val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = TripPathColors.BrandDefault,
    onPrimary = TripPathColors.Common0,
    primaryContainer = TripPathColors.BrandSubtlest,
    onPrimaryContainer = TripPathColors.BrandBold,
    
    secondary = TripPathColors.Neutral60,
    onSecondary = TripPathColors.Common0,
    secondaryContainer = TripPathColors.Neutral95,
    onSecondaryContainer = TripPathColors.Neutral20,
    
    tertiary = TripPathColors.LightBlue,
    onTertiary = TripPathColors.Common0,
    tertiaryContainer = TripPathColors.Neutral99,
    onTertiaryContainer = TripPathColors.Neutral30,
    
    error = TripPathColors.SystemDanger,
    onError = TripPathColors.Common0,
    errorContainer = Color(0xFFFFEDEA),
    onErrorContainer = Color(0xFF8C1D18),
    
    background = TripPathColors.BackgroundWhite,
    onBackground = TripPathColors.TextDefault,
    
    surface = TripPathColors.BackgroundWhite,
    onSurface = TripPathColors.TextDefault,
    surfaceVariant = TripPathColors.BackgroundNeutral,
    onSurfaceVariant = TripPathColors.TextSubtle,
    
    outline = TripPathColors.BorderDefault,
    outlineVariant = TripPathColors.BorderBold,
    
    scrim = Color(0x80000000),
    
    inverseSurface = TripPathColors.Neutral20,
    inverseOnSurface = TripPathColors.Common0,
    inversePrimary = TripPathColors.BrandSubtle,
    
    surfaceDim = TripPathColors.Neutral95,
    surfaceBright = TripPathColors.Common0,
    surfaceContainerLowest = TripPathColors.Common0,
    surfaceContainerLow = TripPathColors.Neutral99,
    surfaceContainer = TripPathColors.Neutral95,
    surfaceContainerHigh = TripPathColors.Neutral90,
    surfaceContainerHighest = TripPathColors.Neutral80
)

val DarkColorScheme = androidx.compose.material3.darkColorScheme(
    primary = TripPathColors.BrandSubtle,
    onPrimary = TripPathColors.BrandBold,
    primaryContainer = TripPathColors.BrandBold,
    onPrimaryContainer = TripPathColors.BrandSubtlest,
    
    secondary = TripPathColors.Neutral70,
    onSecondary = TripPathColors.Neutral20,
    secondaryContainer = TripPathColors.Neutral30,
    onSecondaryContainer = TripPathColors.Neutral90,
    
    tertiary = TripPathColors.LightBlue,
    onTertiary = TripPathColors.Neutral20,
    tertiaryContainer = TripPathColors.Neutral30,
    onTertiaryContainer = TripPathColors.Neutral95,
    
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    
    background = TripPathColors.Neutral10,
    onBackground = TripPathColors.Neutral90,
    
    surface = TripPathColors.Neutral10,
    onSurface = TripPathColors.Neutral90,
    surfaceVariant = TripPathColors.Neutral30,
    onSurfaceVariant = TripPathColors.Neutral70,
    
    outline = TripPathColors.Neutral60,
    outlineVariant = TripPathColors.Neutral30,
    
    scrim = TripPathColors.Common100,
    
    inverseSurface = TripPathColors.Neutral90,
    inverseOnSurface = TripPathColors.Neutral20,
    inversePrimary = TripPathColors.BrandDefault,
    
    surfaceDim = TripPathColors.Neutral10,
    surfaceBright = TripPathColors.Neutral30,
    surfaceContainerLowest = TripPathColors.Neutral0,
    surfaceContainerLow = TripPathColors.Neutral10,
    surfaceContainer = TripPathColors.Neutral20,
    surfaceContainerHigh = TripPathColors.Neutral30,
    surfaceContainerHighest = TripPathColors.Neutral40,
)