package com.app.assignmentapp

data class Post(
    val id: Int,
    val name: String,
    val profile: Int,
    val type: String,
    val time: Int,
    val text: String,
    val image: ArrayList<Int>,
    val likes: Int,
    val comments: Int
)
