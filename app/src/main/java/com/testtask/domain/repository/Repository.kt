package com.testtask.domain.repository

import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo

interface Repository {
    suspend fun getPosts(isFresh: Boolean): List<Post>?
    suspend fun getCommentsForPost(postId: Long, isFresh: Boolean): List<Comment>?
    suspend fun getUserById(id: Long, isFresh: Boolean): UserInfo?
}