package com.example.myapplication.model

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: DataContainer<T>
)
