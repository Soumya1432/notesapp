package com.example.notesapp.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.viewmodel.AuthViewModel

@Composable
fun OtpScreen(navController: NavController,viewModel: AuthViewModel,email: String){
    val otpLength = 6
    val otpValues = List(otpLength) { remember { mutableStateOf("") }}
    val focusRequesters = remember { List(otpLength) { FocusRequester()} }
   // val otp = remember { mutableStateOf("") }
    val otp = otpValues.joinToString("") { it.value }
    val context = LocalContext.current
    val message = viewModel.authMessage.collectAsState()
  //  val email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
    ) {
        Column(modifier = Modifier.fillMaxSize()
            .padding(
                16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Registration",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Please enter your OTP to complete registration",
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 8.dp)
            )

            // otp boxes
            Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                otpValues.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value=value.value,
                        onValueChange = {
                            value.value=it
                            if(it.length<=1){
                                value.value=it
                                if(it.isNotEmpty() && index< otpLength-1){
                                    focusRequesters[index+1].requestFocus()
                                }
                            }
                        },
                        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Medium),
                        modifier = Modifier.width(48.dp).height(56.dp).focusRequester(focusRequesters[index])
                            .onKeyEvent {
                                if(it.key == Key.Backspace && value.value.isEmpty()){
                                    if(index > 0){
                                        focusRequesters[index-1].requestFocus()
                                    }
                                }
                                false
                            },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
            LaunchedEffect(Unit) {
                focusRequesters[0].requestFocus()
            }

            Text(
                text = "0:24",
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Gray
            )

            // Resend Text
            Text(
                text = "Didn't receive OTP?",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.Gray
            )

            Text(
                text = "Resend OTP",
                modifier = Modifier.padding(top = 4.dp),
                color = Color(0xFFFFC107), // Yellow-like
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(24.dp)
                ) {
                    // Title, subtitle, textfield
                }

                Button(
                    onClick = {
                        navController.navigate("todo")
                        viewModel.verifyOtp(email, otp)
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
                    Text("Verify Otp")
                }

                // Show response message
//                Text(
//                    text = message.value,
//                     color = if (message.value.contains("Error", true)) Color.Red else Color.Green,
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .padding(16.dp),
//                    textAlign = TextAlign.Center
//                )

                LaunchedEffect(message.value) {
                    if (message.value.contains("success", ignoreCase = true)) {
                        Toast.makeText(context, "OTP verified successfully!", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }


    }
