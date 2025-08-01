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
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.network.Todo
import com.example.notesapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

@Composable
fun TodoListScreen(viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()
    val context = LocalContext.current
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
            label = { Text("Title", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description",color = Color.Black) },
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
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
                        NotificationUtils.showNotification(
                            context = context,
                            title = "New TODO Added",
                            description = title
                        )
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

                            Text(text = todo.title, color = Color.Black, fontWeight = FontWeight.Bold)
                            Text(text = todo.description, color = Color.DarkGray, fontWeight = FontWeight.Normal)

                            Text(text = formatCreatedTime(createdAtString = todo.createdAt), color = Color.Gray)
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

//fun formatCreatedTime(createdAt: Long): String {
//    val now = System.currentTimeMillis()
//    val diff = now - createdAt
//
//    // Format for display
//    val dateFormat = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
//    val createdDate = Date(createdAt)
//    val formattedDate = dateFormat.format(createdDate)
//
//    // Time ago logic
//    val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
//    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
//    val hours = TimeUnit.MILLISECONDS.toHours(diff)
//    val days = TimeUnit.MILLISECONDS.toDays(diff)
//
//    val timeAgo = when {
//        seconds < 60 -> "$seconds seconds ago"
//        minutes < 60 -> "$minutes minutes ago"
//        hours < 24 -> "$hours hours ago"
//        else -> "$days days ago"
//    }
//
//    return "$formattedDate · $timeAgo"
//}


fun formatCreatedTime(createdAtString: String): String {
    return try {
        // Handle ISO 8601 with or without milliseconds
        val cleanedDate = createdAtString.replace("Z", "").replace("T", " ")
        val inputFormat = when {
            cleanedDate.contains('.') -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
            else -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        }

        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(cleanedDate)
        val createdAt = date?.time ?: return "Invalid time"

        val now = System.currentTimeMillis()
        val diff = now - createdAt

        val outputFormat = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        val formattedDate = outputFormat.format(Date(createdAt))

        val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val days = TimeUnit.MILLISECONDS.toDays(diff)

        val timeAgo = when {
            seconds < 60 -> "$seconds seconds ago"
            minutes < 60 -> "$minutes minutes ago"
            hours < 24 -> "$hours hours ago"
            else -> "$days days ago"
        }

        "$formattedDate · $timeAgo"
    } catch (e: Exception) {
        "Invalid date"
    }
}
