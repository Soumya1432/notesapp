package com.example.notesapp.network


import com.example.notesapp.viewmodel.TodoViewModel
import retrofit2.Response
import retrofit2.http.*

interface  ApiService{

    @GET("todo")
    suspend fun getAllTodos():List<Todo>
//    suspend fun getAllTodos():Response<TodoViewModel>
  @POST("todo")
  suspend fun  addTodo(@Body todo:Todo):Todo

  @PATCH("todo/{id}")
  suspend fun updateTodo(@Path("id") id:String,@Body todo: Todo):Todo

    @DELETE("todo/{id}")
    suspend fun deleteTodo(@Path("id") id: String): Response<Todo>

}