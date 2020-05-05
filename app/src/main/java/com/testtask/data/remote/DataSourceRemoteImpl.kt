package com.testtask.data.remote

import com.testtask.data.ApiRetrofit
import com.testtask.data.datasource.DataSourceRemote
import com.testtask.data.mapper.DataMapper
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo
import javax.inject.Inject

class DataSourceRemoteImpl @Inject constructor(
    private val apiRetrofit: ApiRetrofit,
    private val mapper: DataMapper
) : DataSourceRemote {

    override suspend fun getPosts(): List<Post>? {
        return try {
            apiRetrofit.getPosts()?.map {
                mapper.toDomain(it)
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getCommentsForPost(postId: Long): List<Comment>? {
        return try {
            apiRetrofit.getCommentsForPost(postId)?.map { mapper.toDomain(it) }
        } catch (e: Exception) {
            null
        }
    }


    override suspend fun getUserById(userId: Long): UserInfo? {
        return try {
            apiRetrofit.getUserById(userId)?.let { mapper.toDomain(it) }
        } catch (e: Exception) {
            null
        }
    }
}