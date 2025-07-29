package com.example.notesapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notesapp.viewmodel.TodoViewModel

@Composable
fun TodoScreen(navController: NavController){
    val viewModel = TodoViewModel()
    TodoListScreen(viewModel)
}