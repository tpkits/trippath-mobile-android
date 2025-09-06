package com.sejun2.trippath.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface TripPathNavKey : NavKey

@Serializable
data object IntroNavKey : TripPathNavKey

@Serializable
data object MainNavKey : TripPathNavKey

@Serializable
data object HomeNavKey : TripPathNavKey