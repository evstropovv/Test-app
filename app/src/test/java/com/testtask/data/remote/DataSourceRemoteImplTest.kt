package com.testtask.data.remote

import com.nhaarman.mockitokotlin2.given
import com.testtask.data.ApiRetrofit
import com.testtask.data.mapper.DataMapper
import com.testtask.data.models.CommentDto
import com.testtask.data.models.PostDto
import com.testtask.data.models.UserDto
import com.testtask.domain.model.Comment
import com.testtask.domain.model.Post
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
            //given
            val answer = getNotEmptyPostList();

            given(apiRetrofit.getPosts())
                .willReturn(answer)

            // when
            val posts = cut.getPosts()

            val mappedPosts: MutableList<Post> = ArrayList()
            answer.iterator().forEach {
                mappedPosts.add(mapper.toDomain(it))
            }

            assert(posts.isNotEmpty())
            assertEquals(posts, mappedPosts)
        }
    }

    @Test
    fun `getPosts return empty list if response is null`() {
        runBlocking {
            //given

            given(apiRetrofit.getPosts())
                .willReturn(null)

            // when
            val posts = cut.getPosts()
            assert(posts.isEmpty())
            assertEquals(posts, ArrayList<Post>())
        }
    }


    @Test
    fun `getCommentsForPost return mapped data from remote data source`() {
        runBlocking {
            //given
            val answer = getNotEmptyCommentList();

            given(apiRetrofit.getCommentsForPost(1))
                .willReturn(answer)

            // when
            val comments = cut.getCommentsForPost(1)

            val mappedComments: MutableList<Comment> = ArrayList()
            answer.iterator().forEach {
                mappedComments.add(mapper.toDomain(it))
            }

            assert(comments.isNotEmpty())
            assertEquals(comments, mappedComments)
        }
    }

    @Test
    fun `getCommentsForPost return empty list if response is null`() {
        runBlocking {
            //given
            given(apiRetrofit.getCommentsForPost(1))
                .willReturn(null)

            // when
            val comments = cut.getCommentsForPost(1)
            assert(comments.isEmpty())
            assertEquals(comments, ArrayList<Comment>())
        }
    }

    @Test
    fun `getUserById return mapped user from remote data source`() {
        runBlocking {
            //given
            val answer = getDefaultUser()

            given(apiRetrofit.getUserById(1))
                .willReturn(answer)

            // when
            val user = cut.getUserById(1)

            assertEquals(user, mapper.toDomain(answer))
        }
    }

    @Test
    fun `getUserById return default empty user if response is null`() {
        runBlocking {
            //given
            given(apiRetrofit.getUserById(1))
                .willReturn(null)

            // when
            val user = cut.getUserById(1)
            assertEquals(user, mapper.toDomain(getDefaultUser()))
        }
    }


    private fun getNotEmptyPostList(): List<PostDto> {
        val answer: MutableList<PostDto> = ArrayList()
        answer.add(PostDto())
        return answer;
    }

    private fun getNotEmptyCommentList(): List<CommentDto> {
        val answer: MutableList<CommentDto> = ArrayList()
        answer.add(CommentDto())
        return answer;
    }

    private fun getDefaultUser(): UserDto {
        return UserDto()
    }
}