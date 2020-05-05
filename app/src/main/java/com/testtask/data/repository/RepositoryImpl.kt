package com.testtask.data.repository

import com.testtask.data.datasource.DataSourceCache
import com.testtask.data.datasource.DataSourceRemote
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo
import com.testtask.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSourceRemote: DataSourceRemote,
    private val dataSourceCache: DataSourceCache
) : Repository {

    override suspend fun getPosts(isFresh: Boolean): List<Post>? {
        val posts = dataSourceRemote.getPosts()
        posts?.let {
            dataSourceCache.savePosts(it)
            return it
        }

        if (isFresh)
            return null

        return dataSourceCache.getPosts()
    }

    override suspend fun getCommentsForPost(postId: Long, isFresh: Boolean): List<Comment>? {
        val comments = dataSourceRemote.getCommentsForPost(postId)
        comments?.let {
            dataSourceCache.saveComments(postId, it)
            return it
        }

        if (isFresh)
            return null

        return dataSourceCache.getCommentsForPost(postId)
    }

    override suspend fun getUserById(id: Long, isFresh: Boolean): UserInfo? {
        val user = dataSourceRemote.getUserById(id)
        user?.let {
            dataSourceCache.saveUser(it)
            return it
        }

        if (isFresh)
            return null

        return dataSourceCache.getUserById(id)
    }
}