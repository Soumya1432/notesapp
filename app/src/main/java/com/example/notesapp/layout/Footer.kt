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
