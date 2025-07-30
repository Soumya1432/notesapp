package com.example.notesapp.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/signup")
    suspend fun  register(@Body request: AuthRequest):Response<AuthResponse>

    @POST("/auth/login")
    suspend fun  login(@Body request: AuthRequest):Response<AuthResponse>

    @POST("/auth/verify-email")
    suspend fun  verifyEmail(@Body request: OtpVerifyRequest):Response<AuthResponse>

    @POST("/auth/logout")
    suspend fun  logout(@Body request: AuthRequest):Response<AuthResponse>


}