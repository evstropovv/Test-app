package com.testtask.data.models

data class CommentDto(
    val postId: Long = 0,
    val id: Long = 0,
    val name: String = "",
    val email: String = "",
    val body: String = ""
)