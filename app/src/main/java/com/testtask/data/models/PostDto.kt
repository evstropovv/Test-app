package com.testtask.data.models

data class PostDto(
    val userId: Long = 0,
    val id: Long = 0,
    val title: String = "",
    val body: String = ""
)