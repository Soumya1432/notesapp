package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.screen.HomeScreen
import com.example.notesapp.screen.TodoListScreen
import com.example.notesapp.screen.TodoScreen

@Composable
fun AppNavigation(){
    var navController = rememberNavController()
    NavHost(navController=navController, startDestination = "home") {
        composable("home"){
        HomeScreen(navController)
        }
        composable("todo"){
            TodoScreen(navController)
        }
    }
}