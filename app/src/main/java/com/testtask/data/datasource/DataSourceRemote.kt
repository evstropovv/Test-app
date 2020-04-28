package com.testtask.data.datasource

import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo

interface DataSourceRemote {
    suspend fun getPosts(): List<Post>
    suspend fun getCommentsForPost(postId: Long): List<Comment>
    suspend fun getUserById(userId: Long): UserInfo?
}