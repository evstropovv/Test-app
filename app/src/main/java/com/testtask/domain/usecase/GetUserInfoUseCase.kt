package com.testtask.domain.usecase

import com.testtask.domain.model.UserInfo
import com.testtask.domain.repository.Repository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: Repository) {

    suspend fun execute(userId: Long, isFresh: Boolean): UserInfo? {
        return repository.getUserById(userId, isFresh)
    }
}