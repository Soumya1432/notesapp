package com.example.notesapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapp.R

@Composable
fun SplashScreen(navController: NavController) {
    val imageList = listOf(
        R.drawable.image4,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image1
    )
    Column(modifier = Modifier.padding(
        bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    )) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                       24.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                LazyRow (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                ) {
                    items(imageList) { imageRes ->
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Carousel Image",
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .width(320.dp)
                                .height(420.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }


//            // Top Image
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "Top Image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(250.dp),
//                contentScale = ContentScale.Crop
//            )

                // Text Below Image
                Text(
                    text = "Welcome to Notely",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Color.Black
                )

                // Horizontal Image Scrolling

                // Bottom Text
                Text(
                    text = "Start organizing your thoughts beautifully.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Get Started Button
                Button(
                    onClick = {
                        navController.navigate("signin") // or whatever your next screen is
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .height(55.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF6200EE)
                        containerColor = Color.Yellow
                    )
                ) {
                    Text(text = "Get Started", fontSize = 14.sp, color = Color.Black)
                }
            }
        }
    }


}
