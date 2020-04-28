package com.testtask.domain.model

import com.testtask.domain.model.delegate.DisplayableItem

data class Comment(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
) : DisplayableItem