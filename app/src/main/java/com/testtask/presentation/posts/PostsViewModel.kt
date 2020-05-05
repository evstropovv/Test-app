package com.testtask.presentation.posts

import androidx.lifecycle.viewModelScope
import com.testtask.domain.model.Post
import com.testtask.domain.model.delegate.DisplayableItem
import com.testtask.domain.usecase.GetPostsUseCase
import com.testtask.library_base.presentation.viewmodel.BaseAction
import com.testtask.library_base.presentation.viewmodel.BaseViewModel
import com.testtask.library_base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsListUseCase: GetPostsUseCase
) : BaseViewModel<PostsViewModel.ViewState, PostsViewModel.Action>(ViewState()) {

    var job: Job? = null

    override fun onLoadData(isFresh: Boolean) {
        loadPosts(isFresh)
    }

    fun loadPosts(isFresh: Boolean) {
        job?.cancel()
        job = viewModelScope.launch {
            getPostsListUseCase.execute(isFresh).also {
                if (it != null) {
                    sendAction(Action.PostsLoadSuccess(it))
                } else {
                    sendAction(Action.PostLoadFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.PostsLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            displayableItems = viewAction.postsList
        )
        is Action.PostLoadFailure -> state.copy(
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
        class PostsLoadSuccess(val postsList: List<Post>) : Action()
        object PostLoadFailure : Action()
    }

}