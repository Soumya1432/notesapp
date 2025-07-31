package com.example.notesapp.screen


import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.example.notesapp.viewmodel.AuthViewModel


@Composable
fun SignupScreen(navController: NavController,viewModel: AuthViewModel) {
//    val phoneNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val message = viewModel.authMessage.collectAsState()

    val context = LocalContext.current
    // val message by viewModel.authMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
    ) {

        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            // Title Text
            Text(
                text = "Registration",
                modifier = Modifier
                    .padding(top = 80.dp)
                    .fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
            Text(
                text = "Please enter your phone number ,we will send otp to verify",
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center

            )

            // Phone number input
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text("Enter Email id....") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                )
                )
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Resend Text
                    Text(
                        text = "have and Account?",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Gray
                    )

                    Text(
                        text = "Signin now",
                        modifier = Modifier.padding(top = 4.dp),
                        color = Color(0xFFFFC107), // Yellow-like
                        fontWeight = FontWeight.SemiBold
                    )

                }


                Button(
                    onClick = {
                        viewModel.registerUser(email.value)
                        navController.navigate("otp/${email.value}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(3.dp)
                        ),
                    contentPadding = PaddingValues(vertical = 18.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFEB3B),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Signup Now")
                }

                LaunchedEffect(message.value) {

                        message.value.let{
                            if(it.isNotBlank()){
                                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()

                                if(it.contains("OTP", ignoreCase = true) ||  it.contains("success", ignoreCase = true)){
                                    navController.navigate("otp/${email.value}")
                                }
                            }

                        }

//                    if (message.value.contains("success", ignoreCase = true)) {
//                        Toast.makeText(context, "OTP verified successfully!", Toast.LENGTH_SHORT).show()
//                    }
                }
            }

        }

    }
}