package com.testtask.data.mapper

import com.testtask.data.models.CommentDto
import com.testtask.data.models.PostDto
import com.testtask.data.models.UserDto
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import com.testtask.domain.model.UserInfo
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun toDomain(commentDto: CommentDto): Comment {
        return Comment(
            postId = commentDto.postId,
            id = commentDto.id,
            name = commentDto.name,
            email = commentDto.email,
            body = commentDto.body
        )
    }

    fun toDomain(postDto: PostDto): Post {
        return Post(
            userId = postDto.userId,
            id = postDto.id,
            title = postDto.title,
            body = postDto.body
        )
    }

    fun toDomain(userDto: UserDto): UserInfo {
        return UserInfo(
            id = userDto.id,
            name = userDto.name,
            userName = userDto.username,
            address = "${userDto.address.city}, ${userDto.address.street}",
            phone = userDto.phone,
            website = userDto.website,
            companyName = userDto.company.name
        )
    }
}