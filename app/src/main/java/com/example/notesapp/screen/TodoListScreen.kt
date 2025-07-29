package com.example.notesapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.network.Todo
import com.example.notesapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch

@Composable
fun TodoListScreen(viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Track whether we're editing or adding
    var isEditing by remember { mutableStateOf(false) }
    var editingTodoId by remember { mutableStateOf<String?>(null) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.fetchTodos()
    }

    // Auto scroll when new todo added
    LaunchedEffect(todos.size) {
        if (todos.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(todos.lastIndex)
            }
        }
    }

    val inputColor = Color.Blue

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = description,

            onValueChange = { description = it },
            label = { Text("Description") }
        )

        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    if (isEditing && editingTodoId != null) {
                        val updatedTodo = todos.find { it.id == editingTodoId }
                        if (updatedTodo != null) {
                            viewModel.updateTodo(
                                updatedTodo.copy(
                                    title = title,
                                    description = description
                                )
                            )
                        }
                        isEditing = false
                        editingTodoId = null
                    } else {
                        viewModel.addTodo(title, description)
                    }

                    title = ""
                    description = ""
                }
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = if (isEditing) Color.Yellow else Color.Green)
        ) {
            Text(if (isEditing) "Update Todo" else "Add Todo", color = Color.Black)
        }

        LazyColumn(state = listState) {
            items(todos) { todo ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = todo.title, color = Color.Black)
                            Text(text = todo.description, color = Color.DarkGray)
                        }

                        Row {
                            IconButton(onClick = {
                                title = todo.title
                                description = todo.description
                                editingTodoId = todo.id
                                isEditing = true
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit",
                                    tint = Color.Blue
                                )
                            }
                            IconButton(onClick = {
                                viewModel.deleteTodo(todo.id)
                                if (editingTodoId == todo.id) {
                                    // Reset edit mode if deleted item is being edited
                                    isEditing = false
                                    editingTodoId = null
                                    title = ""
                                    description = ""
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
