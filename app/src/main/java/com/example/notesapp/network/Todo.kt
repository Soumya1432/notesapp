package com.example.notesapp.network

data class Todo(
    val id: String,
    val title: String,
    val description: String, // safer than Any?
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String? // or Any? if truly needed
)
