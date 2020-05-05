package com.testtask.presentation.userinfo

import androidx.lifecycle.viewModelScope
import com.testtask.domain.model.Post
import com.testtask.domain.model.delegate.DisplayableItem
import com.testtask.domain.usecase.GetCommentsUseCase
import com.testtask.domain.usecase.GetUserInfoUseCase
import com.testtask.library_base.presentation.viewmodel.BaseAction
import com.testtask.library_base.presentation.viewmodel.BaseViewModel
import com.testtask.library_base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val userInfoUseCase: GetUserInfoUseCase,
    private val commentsUseCase: GetCommentsUseCase
) : BaseViewModel<UserInfoViewModel.ViewState, UserInfoViewModel.Action>(ViewState()) {

    var selectedPost: Post? = null
    var job: Job? = null

    override fun onLoadData(isFresh: Boolean) {
        selectedPost?.let {
            loadClienInfo(it.userId, it.id, isFresh)
        }
    }

    fun setPost(post: Post) {
        this.selectedPost = post
        loadClienInfo(post.userId, post.id)
    }

    fun loadClienInfo(userId: Long, postId: Long, isFresh: Boolean = false) {
        job?.cancel()
        job = viewModelScope.launch {
            val userInfoDef = async { userInfoUseCase.execute(userId, isFresh) }
            val postsDef = async { commentsUseCase.execute(postId, isFresh) }

            val userInfo = userInfoDef.await()
            val posts = postsDef.await()

            if (userInfo != null && posts != null) {
                val userInfoAndPostsList: MutableList<DisplayableItem> = ArrayList()
                userInfoAndPostsList.add(userInfo)
                userInfoAndPostsList.addAll(posts)
                sendAction(Action.UserInfoSuccess(userInfoAndPostsList))
            } else {
                sendAction(Action.UserInfoLoadFailure)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.UserInfoSuccess -> state.copy(
            isLoading = false,
            isError = false,
            displayableItems = viewAction.postsList
        )
        is Action.UserInfoLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            displayableItems = ArrayList()
        )
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val displayableItems: List<DisplayableItem> = ArrayList()
    ) : BaseViewState

    sealed class Action : BaseAction {
        class UserInfoSuccess(val postsList: List<DisplayableItem>) : Action()
        object UserInfoLoadFailure : Action()
    }

}