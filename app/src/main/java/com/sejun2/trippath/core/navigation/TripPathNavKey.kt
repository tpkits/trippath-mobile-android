package com.sejun2.trippath.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object IntroNavKey : NavKey

@Serializable
data object MainNavKey : NavKey

@Serializable
data object HomeNavKey : NavKey

@Serializable
data object LoginDialogNavKey : NavKey
