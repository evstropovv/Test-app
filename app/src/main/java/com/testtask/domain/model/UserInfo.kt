package com.testtask.domain.model

import com.testtask.domain.model.delegate.DisplayableItem

data class UserInfo(
    val id: Long,
    val name: String,
    val userName: String,
    val address: String,
    val phone: String,
    val website: String,
    val companyName: String
) : DisplayableItem