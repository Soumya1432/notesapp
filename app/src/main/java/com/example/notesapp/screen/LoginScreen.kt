package com.example.notesapp.screen

import android.graphics.Paint.Align
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//@Composable
//fun LoginScreen(navController: NavController) {
//    val textState = remember { mutableStateOf("") }
//
//    Column {
//        Button(onClick = { navController.popBackStack() }) {
//            Text("Back to Home")
//        }
//
//        Row(modifier = Modifier.border(width = 14.dp, color = Color.LightGray, shape = RectangleShape)
//            .fillMaxWidth()
//            .padding(48.dp))
//
//        {
//
//            TextField(
//                value = textState.value,
//                onValueChange = { textState.value = it },
//                label = {
//                    Text(
//                        text = "Enter Mobile Number",
//                        fontStyle = FontStyle.Italic,
//                        color = Color.Magenta
//                    )
//                },
//                placeholder = {
//                    Text("Ex: 9876543210", color = Color.Gray)
//                },
//                modifier = Modifier
//                    .border(
//                        width = 2.dp,
//                        color = Color.LightGray,
//                        shape = RoundedCornerShape(8.dp)
//                    )
//                    .fillMaxWidth()
//                    .padding(4.dp)
//            )
//        }
//    }
//}

@Composable
fun LoginScreen(navController: NavController) {
    val phoneNumber = remember { mutableStateOf("") }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

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
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            placeholder = { Text("Enter Mobile No....") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            shape = RoundedCornerShape(8.dp),

            )
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
                onClick = { /* Send OTP logic */ },
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
                Text("Request Otp")
            }
        }

    }
}