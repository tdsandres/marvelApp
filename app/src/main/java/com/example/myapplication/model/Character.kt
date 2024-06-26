package com.example.myapplication.model

import android.media.Image

data class Character(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : Thumbnail,
    // val comics : ArrayList<Comic>
    // val stories : ArrayList<Story>
    // val events : ArrayList<Event>
    // val series : ArrayList<Series>
)
