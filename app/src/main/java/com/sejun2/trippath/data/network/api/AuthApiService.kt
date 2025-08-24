package com.sejun2.trippath.data.network.api

import com.sejun2.trippath.data.network.dto.request.LoginRequest
import com.sejun2.trippath.data.network.dto.response.LoginResponse
import com.sejun2.trippath.data.network.dto.response.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {

    @POST("auth/mobile/login/{provider}")
    suspend fun mobileLogin(
        @Path("provider") provider: String,
        @Body idToken: LoginRequest
    ): Response<LoginResponse>

    @POST("auth/mobile/logout")
    suspend fun mobileLogout(): Response<Unit>

    @POST("auth/mobile/reissue/token")
    suspend fun reissueToken(): Response<LoginResponse>

    @GET("me")
    suspend fun getUserInfo(): Response<UserInfoResponse>
}
