package com.testtask.presentation.userinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.testtask.CoroutineRule
import com.testtask.data.TestData
import com.testtask.data.mapper.DataMapper
import com.testtask.domain.model.delegate.DisplayableItem
import com.testtask.domain.usecase.GetCommentsUseCase
import com.testtask.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserInfoViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    internal lateinit var userInfoUseCase: GetUserInfoUseCase

    @Mock
    internal lateinit var commentsUseCase: GetCommentsUseCase

    private lateinit var cut: UserInfoViewModel

    private lateinit var mapper: DataMapper

    @Before
    fun setUp() {
        mapper = DataMapper()
        cut = UserInfoViewModel(
            userInfoUseCase,
            commentsUseCase
        )
    }

    @Test
    fun `execute userInfoUseCase and commentsUseCase after setPost`() {

        cut.setPost(TestData.getPost())

        runBlocking {
            verify(userInfoUseCase).execute(1, false)
            verify(commentsUseCase).execute(2, false)
        }

    }

    @Test
    fun `verify state when UserInfoUseCase and CommentsUseCase returns non-empty data`() {

       userInfoUseCase.stub {
            onBlocking { execute(1, false) } doReturn
                    TestData.getDefaultUser()
        }

        commentsUseCase.stub {
            onBlocking { execute(2, false) } doReturn
                    TestData.getNotEmptyCommentList()
        }

        //when
        cut.setPost(TestData.getPost())

        val displayableItem : MutableList<DisplayableItem> = ArrayList()
        displayableItem.add(TestData.getDefaultUser())
        displayableItem.addAll(TestData.getNotEmptyCommentList())

        // then
        assertEquals(cut.stateLiveData.value, UserInfoViewModel.ViewState(
            isLoading = false,
            isError = false,
            displayableItems = displayableItem))

    }


    @Test
    fun `verify state when UserInfoUseCase and CommentsUseCase returns empty data`() {

        userInfoUseCase.stub {
            onBlocking { execute(1, false) } doReturn
                    null
        }

        commentsUseCase.stub {
            onBlocking { execute(2, false) } doReturn
                  null
        }

        //when
        cut.setPost(TestData.getPost())

        // then
        assertEquals(cut.stateLiveData.value, UserInfoViewModel.ViewState(
            isLoading = false,
            isError = true,
            displayableItems = ArrayList<DisplayableItem>()))

    }
}