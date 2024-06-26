package com.example.myapplication.model

data class Comic(
    val id : Int,
    val title: String,
    val pageCount: Int,
    val thumbnail: Thumbnail,
    val description: String
)
