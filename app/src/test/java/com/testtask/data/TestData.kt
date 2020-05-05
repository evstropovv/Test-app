package com.testtask.data

import com.testtask.data.models.CommentDto
import com.testtask.data.models.PostDto
import com.testtask.data.models.UserDto
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo

object TestData {
    fun getNotEmptyPostList(): List<Post> {
        val answer: MutableList<Post> = ArrayList()
        answer.add(Post(1, 1, "", ""))
        return answer
    }

    fun getNotEmptyCommentList(): List<Comment> {
        val answer: MutableList<Comment> = ArrayList()
        answer.add(Comment(1, 1, "", "", ""))
        return answer
    }

    fun getDefaultUser(): UserInfo {
        return UserInfo(1, "", "", "", "", "", "")
    }

    fun getDefaultUserDto(): UserDto {
        return UserDto(
            1, "", "", "",
            UserDto.AddressDto(
                "", "", "", "",
                UserDto.AddressDto.GeoDto(0.0, 0.0)
            ), "", "", UserDto.CompanyDto("", "", "")
        )
    }

    fun getNotEmptyPostDtoList(): List<PostDto> {
        val answer: MutableList<PostDto> = ArrayList()
        answer.add(getPostDto())
        return answer
    }

    fun getNotEmptyCommentDtoList(): List<CommentDto> {
        val answer: MutableList<CommentDto> = ArrayList()
        answer.add(getCommentDto())
        return answer
    }

    fun getPostDto(): PostDto = PostDto(0, 0, "", "")

    fun getCommentDto(): CommentDto = CommentDto(0, 0, "", "", "")

    fun getPost(): Post = Post(userId = 1, id = 2, title = "", body = "")

}