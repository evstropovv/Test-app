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

    override suspend fun getPosts(isFresh: Boolean): List<Post> {
        var posts = when (isFresh) {
            true -> dataSourceRemote.getPosts()
            false -> dataSourceCache.getPosts()
        }
        if (posts.isEmpty()) {
            posts = dataSourceRemote.getPosts()
            dataSourceCache.savePosts(posts)
        }
        return posts
    }

    override suspend fun getCommentsForPost(postId: Long, isFresh: Boolean): List<Comment> {
        var comments = when (isFresh) {
            true -> dataSourceRemote.getCommentsForPost(postId)
            false -> dataSourceCache.getCommentsForPost(postId)
        }
        if (comments.isEmpty()) {
            comments = dataSourceRemote.getCommentsForPost(postId)
            dataSourceCache.saveComments(postId, comments)
        }
        return comments
    }

    override suspend fun getUserById(id: Long, isFresh: Boolean): UserInfo? {
        var user = when (isFresh) {
            true -> dataSourceRemote.getUserById(id)
            false -> dataSourceCache.getUserById(id)
        }
        if (user == null) {
            user = dataSourceRemote.getUserById(id)
            user?.let{
                dataSourceCache.saveUser(it)
            }
        }
        return user
    }
}