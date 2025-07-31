package com.example.notesapp.layout


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun Navbar(){

    var isSearchVisible by remember { mutableStateOf(false) }

   var showNotificationDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(
            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
        )
    ) {

    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
//            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp), clip = false)
        ,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(onClick = { showNotificationDialog=true}) {
                Icon(Icons.Default.Notifications, contentDescription = "Notification", tint = Color.Black)
            }

            IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Black)
            }
        }
    }

    AnimatedVisibility(
        visible = isSearchVisible,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally()
        ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search...") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
    }

    if(showNotificationDialog){
        Dialog(onDismissRequest = {showNotificationDialog=false}) {
            NotificationBox()
        }
    }
}

@Composable
fun NotificationBox(){
    Box(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
            .background(Color(0xFFF9F9F9),
                shape = RoundedCornerShape(16.dp)
                )
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ){
        Text(text = "No new Notifications", color = Color.Black)
    }
}