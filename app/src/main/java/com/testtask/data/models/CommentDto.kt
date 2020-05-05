package com.testtask.data.models

data class CommentDto(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String)