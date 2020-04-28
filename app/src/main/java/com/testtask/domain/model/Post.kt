package com.testtask.domain.model

import android.os.Parcelable
import com.testtask.domain.model.delegate.DisplayableItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
) : DisplayableItem, Parcelable