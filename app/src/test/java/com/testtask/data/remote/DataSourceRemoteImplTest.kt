package com.testtask.data.remote

import com.nhaarman.mockitokotlin2.given
import com.testtask.data.ApiRetrofit
import com.testtask.data.TestData
import com.testtask.data.mapper.DataMapper
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DataSourceRemoteImplTest {

    @Mock
    internal lateinit var apiRetrofit: ApiRetrofit

    private lateinit var cut: DataSourceRemoteImpl
    private lateinit var mapper: DataMapper

    @Before
    fun setUp() {
        mapper = DataMapper()
        cut = DataSourceRemoteImpl(apiRetrofit, mapper)
    }

    @Test
    fun `getPosts return mapped data from remote data source`() {
        runBlocking {

            val answer = TestData.getNotEmptyPostDtoList()

            given(apiRetrofit.getPosts())
                .willReturn(answer)

            val posts = cut.getPosts()

            val mappedPosts: List<Post> = answer.map {
                 mapper.toDomain(it)
            }

            assertEquals(posts,mappedPosts)
        }
    }

    @Test
    fun `getPosts return null if response is null`() {
        runBlocking {

            given(apiRetrofit.getPosts())
                .willReturn(null)

            val posts = cut.getPosts()
            assertNull(posts)
        }
    }


    @Test
    fun `getCommentsForPost return mapped data from remote data source`() {
        runBlocking {

            val answer = TestData.getNotEmptyCommentDtoList()

            given(apiRetrofit.getCommentsForPost(1))
                .willReturn(answer)

            val comments = cut.getCommentsForPost(1)

            val mappedComments = answer.map {
                mapper.toDomain(it)
            }

            assertEquals(comments, mappedComments)
        }
    }

    @Test
    fun `getCommentsForPost return null if response is null`() {
        runBlocking {

            given(apiRetrofit.getCommentsForPost(1))
                .willReturn(null)

            val comments = cut.getCommentsForPost(1)
            assertNull(comments)
        }
    }

    @Test
    fun `getUserById return mapped user from remote data source`() {
        runBlocking {
            val answer = TestData.getDefaultUserDto()

            given(apiRetrofit.getUserById(1))
                .willReturn(answer)

            val user = cut.getUserById(1)

            assertEquals(user, mapper.toDomain(answer))
        }
    }

    @Test
    fun `getUserById return null if response is null`() {
        runBlocking {

            given(apiRetrofit.getUserById(1))
                .willReturn(null)

            val user = cut.getUserById(1)
            assertNull(user)
        }
    }


}