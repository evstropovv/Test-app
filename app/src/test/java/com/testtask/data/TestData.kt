package com.testtask.data

import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo

object TestData {
    fun getNotEmptyPostList(): List<Post> {
        val answer: MutableList<Post> = ArrayList()
        answer.add(Post(1, 1, "", ""))
        return answer;
    }

    fun getNotEmptyCommentList(): List<Comment> {
        val answer: MutableList<Comment> = ArrayList()
        answer.add(Comment(1, 1, "", "", ""))
        return answer;
    }

    fun getDefaultUser(): UserInfo {
        return UserInfo(1, "", "", "", "", "", "")
    }
}