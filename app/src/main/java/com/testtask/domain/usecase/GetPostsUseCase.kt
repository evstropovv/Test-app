package com.testtask.domain.usecase

import com.testtask.domain.model.Post
import com.testtask.domain.repository.Repository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(isFresh : Boolean): List<Post> {
        return repository.getPosts(isFresh)
    }
}