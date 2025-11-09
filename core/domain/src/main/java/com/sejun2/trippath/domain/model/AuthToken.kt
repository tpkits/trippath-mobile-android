package com.sejun2.trippath.domain.model

data class AuthToken(
    val accessToken: String,
    val refreshToken: String
)
