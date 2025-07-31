package com.example.notesapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notesapp.network.ApiClient
import com.example.notesapp.viewmodel.AuthViewModel

@Composable
fun SigninScreen(navController: NavController){
    val viewModel = AuthViewModel(ApiClient.authService)
    LoginScreen(navController,viewModel)
}


