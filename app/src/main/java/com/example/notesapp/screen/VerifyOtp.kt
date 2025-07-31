package com.example.notesapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notesapp.network.ApiClient
import com.example.notesapp.viewmodel.AuthViewModel

@Composable
fun VerifyOtp(navController: NavController, email: String){
    val viewModel = AuthViewModel(ApiClient.authService)
    OtpScreen(navController, viewModel, email)
}
