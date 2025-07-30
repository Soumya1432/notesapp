package com.example.notesapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OtpScreen(navController: NavController){
    val otpLength = 6
    val otpValues = List(otpLength) { remember { mutableStateOf("") }}
    val focusRequesters = remember { List(otpLength) { FocusRequester()} }
    Column(modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
//            otpValues.forEach { value ->
//                    OutlinedTextField(
//                        value=value.value,
//                        onValueChange = {
//                            if(it.length <=1 ) value.value = it
//                        },
//                        modifier = Modifier.width(48.dp).height(48.dp),
//                        singleLine = true,
//                        shape = RoundedCornerShape(8.dp),
//                        keyboardOptions = KeyboardOptions.Default.copy(
//                            keyboardType = KeyboardType.Number
//                        )
//                    )
//
//            }

            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    value=value.value,
                    onValueChange = {
                        if(it.length<=1){
                            value.value=it
                            if(it.isNotEmpty() && index< otpLength-1){
                                focusRequesters[index+1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier.width(48.dp).height(48.dp).focusRequester(focusRequesters[index])
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


    }
}