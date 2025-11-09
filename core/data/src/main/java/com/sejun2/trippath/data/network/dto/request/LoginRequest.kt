package com.sejun2.trippath.data.network.dto.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val idToken: String
)