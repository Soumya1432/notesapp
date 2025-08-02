package com.example.notesapp.layout

import android.content.Intent
import android.util.Log
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
import androidx.navigation.NavController
import com.example.notesapp.CameraActivity

@Composable
fun Footer(navController: NavController, onMenuClick: ()-> Unit) {
    val context = androidx.compose.ui.platform.LocalContext.current
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
        IconButton(onClick = {
            val intent = Intent(context, CameraActivity::class.java)
            context.startActivity(intent)
        }) {
            Log.d("Calling camerwa","watch")
            Icon(Icons.Default.LocationOn, contentDescription = "Camera", tint = Color.Black)
        }

    }
}
