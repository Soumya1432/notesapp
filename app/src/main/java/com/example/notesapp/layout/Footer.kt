//package com.example.notesapp.layout
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.asPaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.navigationBars
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBars
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//@Composable
//fun Footer(navController: NavController){
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//
//        Spacer(modifier = Modifier.weight(1f)) // Pushes content to bottom
//
//        // Bottom Navigation Row
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.Yellow)
//                .padding(vertical = 12.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = { navController.navigate("home")}) {
//                Icon(
//                    imageVector = Icons.Default.Home,
//                    contentDescription = "Home Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.size(32.dp)
//                )
//            }
//            IconButton(onClick = { navController.navigate("todo")}) {
//                Icon(
//                    imageVector = Icons.Default.ShoppingCart,
//                    contentDescription = "Cart Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.size(32.dp)
//                )
//            }
//            IconButton(onClick = { /* TODO */ }) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = "Menu Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.size(32.dp)
//                )
//            }
//            IconButton(onClick = { navController.navigate("todo")}) {
//                Icon(
//                    imageVector = Icons.Default.AccountCircle,
//                    contentDescription = "Profile Icon",
//                    tint = Color.Black,
//                    modifier = Modifier.size(32.dp)
//                )
//            }
//        }
//    }
//}
// layout/Footer.kt




package com.example.notesapp.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Footer(navController: NavController, onMenuClick: ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.navigate("home") }) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Black)
        }
        IconButton(onClick = { navController.navigate("todo") }) {
            Icon(Icons.Default.AddCircle, contentDescription = "Todo", tint = Color.Black)
        }
        IconButton(onClick = { onMenuClick()}) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
        }
        IconButton(onClick = { navController.navigate("profile") }) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.Black)
        }
    }
}
