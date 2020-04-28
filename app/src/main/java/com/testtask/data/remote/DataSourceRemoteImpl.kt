package com.testtask.data.remote

import com.testtask.data.ApiRetrofit
import com.testtask.data.datasource.DataSourceRemote
import com.testtask.data.mapper.DataMapper
import com.testtask.data.models.CommentDto
import com.testtask.data.models.PostDto
import com.testtask.data.models.UserDto
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo
import java.lang.Exception
import javax.inject.Inject

class DataSourceRemoteImpl @Inject constructor(
    private val apiRetrofit: ApiRetrofit,
    private val mapper: DataMapper
) : DataSourceRemote {

    override suspend fun getPosts(): List<Post> {
        val posts: List<PostDto>? = try {
            apiRetrofit.getPosts()
        } catch (e: Exception) {
            ArrayList()
        }

        val mappedPosts: MutableList<Post> = ArrayList()
        posts?.iterator()?.forEach {
            mappedPosts.add(mapper.toDomain(it))
        }
        return mappedPosts
    }

    override suspend fun getCommentsForPost(postId: Long): List<Comment> {
        val comments: List<CommentDto>? = try {
            apiRetrofit.getCommentsForPost(postId)
        } catch (e: Exception) {
            ArrayList()
        }

        val mappedComments: MutableList<Comment> = ArrayList()
        comments?.iterator()?.forEach {
            mappedComments.add(mapper.toDomain(it))
        }
        return mappedComments
    }

    override suspend fun getUserById(userId: Long): UserInfo {
        val user:UserDto? = try {
            apiRetrofit.getUserById(userId)
        } catch (e: Exception) {
            UserDto()
        }
        return mapper.toDomain(user?:UserDto())
    }

}