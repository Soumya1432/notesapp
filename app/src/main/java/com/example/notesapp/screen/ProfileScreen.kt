package com.example.notesapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapp.R

@Composable
fun ProfileScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


        // ✅ Properly Centered Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Profile",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)).padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column() {
                        Text(
                            text = "Soumya Biswas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "biswassoumya17@gmail.com",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }


        }

       Spacer(modifier = Modifier.height(16.dp))
        Column {

        }

    }
}