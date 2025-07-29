package com.example.notesapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.network.ApiClient
import com.example.notesapp.network.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel :ViewModel(){
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())

    val todos:StateFlow<List<Todo>> = _todos

    fun fetchTodos(){

        viewModelScope.launch {
            try {
                val result = ApiClient.apiService.getAllTodos()
                _todos.value = result
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to fetch todos", e)
            }

        }
    }

    fun addTodo(title: String,description:String) {
        viewModelScope.launch {
            val newTodo = Todo(
                id = "", // Backend should generate it
                title = title,
                description =description,
                createdAt = "",
                updatedAt = "",
                deletedAt = null
            )
            ApiClient.apiService.addTodo(newTodo)
            fetchTodos()
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            ApiClient.apiService.updateTodo(todo.id, todo)
            fetchTodos()
        }
    }

    fun deleteTodo(id: String) {
        viewModelScope.launch {
            ApiClient.apiService.deleteTodo(id)
            fetchTodos()
        }
    }



}