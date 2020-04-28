package com.testtask.domain.usecase

import com.testtask.domain.model.Comment
import com.testtask.domain.repository.Repository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(postId: Long, isFresh : Boolean): List<Comment> {
        return repository.getCommentsForPost(postId, isFresh)
    }
}