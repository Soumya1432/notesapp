package com.example.notesapp.network


data class AuthRequest(
val email:String
)
data class  OtpVerifyRequest(
    val email: String,
    val otp:String
)

// for signup login response


data class AuthResponse(
    val message: String,
    val data: UserData? = null
)
        data class UserData(
            val id:String,
            val email:String
        )