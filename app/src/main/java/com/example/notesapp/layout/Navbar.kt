package com.example.notesapp.layout


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.contentColorFor
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import retrofit2.http.Query

@Composable
fun Navbar(navController: NavController){

    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var showNotificationDialog by remember { mutableStateOf(false) }
    val keyboardController= LocalSoftwareKeyboardController.current
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
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it

            },
            placeholder = { Text("Search....") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    handleSearchQuery(searchQuery, navController)
                    keyboardController?.hide()
                }
            ),
            )


    }

    if(showNotificationDialog){
        Dialog(onDismissRequest = {showNotificationDialog=false}) {
            NotificationBox()
        }
    }
}

@Composable
fun NotificationBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(360.dp) // Fixed height for the notification box
            .background(Color(0xFFF9F9F9), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            // Replace with dynamic notifications list if needed
            repeat(30) {
                Text(
                    text = "🔔 Notification ${it + 1}",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

fun handleSearchQuery(query: String,navController: NavController){
 when(query.trim().lowercase()){
    "home" -> navController.navigate("home")
     "todo" -> navController.navigate("todo")
     "profile" -> navController.navigate("profile")
     "signup" -> navController.navigate("signup")
 }
}