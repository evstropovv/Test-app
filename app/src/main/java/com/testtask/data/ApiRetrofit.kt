package com.testtask.data

import com.testtask.data.models.CommentDto
import com.testtask.data.models.PostDto
import com.testtask.data.models.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRetrofit {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{id}/comments")
    suspend fun getCommentsForPost(@Path("id") postId: Long): List<CommentDto>

    @GET("users/{userId}/")
    suspend fun getUserById(@Path("userId") userId: Long): UserDto

}