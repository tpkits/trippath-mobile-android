package com.sejun2.trippath.data.network.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfoResponse(
    val email: String,
    val name: String,
)
