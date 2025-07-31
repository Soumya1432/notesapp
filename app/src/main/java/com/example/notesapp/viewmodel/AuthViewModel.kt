package com.example.notesapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.network.AuthRequest
import com.example.notesapp.network.AuthResponse
import com.example.notesapp.network.AuthService
import com.example.notesapp.network.OtpVerifyRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel( private val service:AuthService):ViewModel() {
    private val _authMessage = MutableStateFlow("")
    val authMessage:StateFlow<String> = _authMessage

    fun registerUser(email:String){
        viewModelScope.launch {
            val response = service.register(AuthRequest(email))
            Log.d("Email","Ateepmt $email")
            if(response.isSuccessful){
                _authMessage.value = response.body()?.message ?: "Registration successful"
                Log.d("Response","response struct $response")
            }
            else{
                _authMessage.value= "Error: ${response.code()} ${response.message()}"
                Log.d("Response","response struct $response")
            }
        }
    }

    fun loginUser(email: String){
        viewModelScope.launch {
            val response = service.login(AuthRequest(email))
            if(response.isSuccessful){
                _authMessage.value = response.body()?.message ?: "Login successful"
            }
            else
            {
                _authMessage.value = "Login error: ${response.code()}"
            }
        }
    }

    fun verifyOtp(email: String,otp:String){
        viewModelScope.launch {
            val response = service.verifyEmail(OtpVerifyRequest(email,otp))
            if(response.isSuccessful){
                _authMessage.value = response.body()?.message ?: "Otp verified"
            }
            else
            {
                _authMessage.value = "Otp Verification failed"
            }
        }
    }

    fun logoutUser(email: String){
        viewModelScope.launch {
            val response = service.logout(AuthRequest(email))
            if(response.isSuccessful){
                _authMessage.value = "Logout successful"
            }
            else
            {
                _authMessage.value = "Logout failed"
            }
        }
    }

}