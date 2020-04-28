package com.testtask.di

import com.testtask.presentation.MainActivity
import com.testtask.presentation.posts.PostsFragment
import com.testtask.presentation.userinfo.UserInfoFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(main: MainActivity)
    fun inject(fragment: PostsFragment)
    fun inject(fragment: UserInfoFragment)

}