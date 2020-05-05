package com.testtask.data.cache

import com.testtask.data.datasource.DataSourceCache
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo
import javax.inject.Inject

class DataSourceCacheImpl @Inject constructor() : DataSourceCache {

    private val users: HashMap<Long, UserInfo> = HashMap() // key = id
    private var posts: List<Post>? = null
    private val comments: HashMap<Long, List<Comment>> = HashMap() // key = id

    override suspend fun getPosts(): List<Post>? {
        return posts
    }

    override suspend fun savePosts(posts: List<Post>) {
        this.posts = posts
    }

    override suspend fun getCommentsForPost(postId: Long): List<Comment>? {
        return comments[postId]
    }

    override suspend fun saveComments(postId: Long, comments: List<Comment>) {
        this.comments[postId] = comments
    }

    override suspend fun getUserById(userId: Long): UserInfo? {
        return users[userId]
    }

    override suspend fun saveUser(userInfo: UserInfo) {
        users[userInfo.id] = userInfo
    }
}