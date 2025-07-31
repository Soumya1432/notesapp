package com.example.notesapp.layout


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    onDestinationClick :(route:String) -> Unit
){
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Navigation", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
        DrawerItem("Home",Icons.Default.Home,"home",onDestinationClick)
        DrawerItem("Add Todo",Icons.Default.AddCircle,"todo",onDestinationClick)
        DrawerItem("My Profile",Icons.Default.Person,"profile",onDestinationClick)
        DrawerItem("Logout",Icons.Default.ExitToApp,"profile",onDestinationClick)

    }

}

@Composable
fun DrawerItem(
    label:String,
    icon:androidx.compose.ui.graphics.vector.ImageVector,
    route: String,
    onClick: (String) -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick(route) }.padding(12.dp),
        horizontalArrangement = Arrangement.Start

    ) {

        Icon(imageVector = icon, contentDescription = label)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label)
    }
}