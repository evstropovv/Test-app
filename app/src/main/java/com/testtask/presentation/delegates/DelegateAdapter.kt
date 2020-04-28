package com.testtask.presentation.delegates

import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.testtask.domain.model.Post
import com.testtask.domain.model.delegate.DisplayableItem

class DelegateAdapter constructor() : ListDelegationAdapter<List<DisplayableItem>>() {

    constructor(fragment: Fragment, listener: (Post) -> Unit) : this() {
        delegatesManager.addDelegate(PostDelegate(fragment, listener))
        delegatesManager.addDelegate(UserInfoDelegate(fragment))
        delegatesManager.addDelegate(CommentDelegate(fragment))
    }
}