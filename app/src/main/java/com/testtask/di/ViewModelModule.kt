package com.testtask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testtask.presentation.posts.PostsViewModel
import com.testtask.presentation.userinfo.UserInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    abstract fun bindUserInfoViewModel(viewModel: UserInfoViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?

}