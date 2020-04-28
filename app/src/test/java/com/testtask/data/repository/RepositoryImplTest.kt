package com.uklontest.data.repository

import com.nhaarman.mockitokotlin2.given
import com.uklontest.data.TestData
import com.uklontest.data.cache.DataSourceCacheImpl
import com.uklontest.data.mapper.DataMapper
import com.uklontest.data.models.UserDto
import com.uklontest.data.remote.DataSourceRemoteImpl
import com.uklontest.domain.model.Comment
import com.uklontest.domain.model.Post
import com.uklontest.domain.model.UserInfo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class RepositoryImplTest {

    @Mock
    internal lateinit var dataSourceRemote: DataSourceRemoteImpl

    @Mock
    internal lateinit var dataSourceCache: DataSourceCacheImpl

    private lateinit var cut: RepositoryImpl
    private lateinit var mapper: DataMapper

    @Before
    fun setUp() {
        cut = RepositoryImpl(dataSourceRemote, dataSourceCache)
        mapper = DataMapper()
    }

    @Test
    fun `fresh getPosts return data from remote data source`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyPostList();

            given(dataSourceRemote.getPosts())
                .willReturn(answer)
            given(dataSourceCache.getPosts())
                .willReturn(ArrayList())

            // when
            val result = cut.getPosts(true)

            assert(result.isNotEmpty())
            assertEquals(answer, result)
        }
    }

    @Test
    fun `getPosts return non-empty list when cache is not empty`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyPostList();

            given(dataSourceRemote.getPosts())
                .willReturn(ArrayList())
            given(dataSourceCache.getPosts())
                .willReturn(answer)

            // when
            val posts = cut.getPosts(false)
            assert(posts.isNotEmpty())
            assertEquals(answer, posts)
        }
    }

    @Test
    fun `getPosts return empty list when cache and remote are empty`() {
        runBlocking {
            //given
            given(dataSourceRemote.getPosts())
                .willReturn(ArrayList())
            given(dataSourceCache.getPosts())
                .willReturn(ArrayList())

            // when
            val posts = cut.getPosts(false)
            assert(posts.isEmpty())
        }
    }

    @Test
    fun `fresh getCommentsForPost return data from remote data source`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyCommentList();

            given(dataSourceRemote.getCommentsForPost(1))
                .willReturn(answer)
            given(dataSourceCache.getCommentsForPost(1))
                .willReturn(ArrayList())

            // when
            val comments = cut.getCommentsForPost(1,true)

            assert(comments.isNotEmpty())
            assertEquals(answer, comments)
        }
    }

    @Test
    fun `getCommentsForPost return non-empty list when cache is not empty`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyCommentList();

            given(dataSourceRemote.getCommentsForPost(1))
                .willReturn(ArrayList())
            given(dataSourceCache.getCommentsForPost(1))
                .willReturn(answer)

            // when
            val comments = cut.getCommentsForPost(1,false)
            assert(comments.isNotEmpty())
            assertEquals(answer, comments)
        }
    }

    @Test
    fun `getCommentsForPost return empty list when cache and remote are empty`() {
        runBlocking {
            //given
            given(dataSourceRemote.getCommentsForPost(1))
                .willReturn(ArrayList())
            given(dataSourceCache.getCommentsForPost(1))
                .willReturn(ArrayList())

            // when
            val comments = cut.getCommentsForPost(1,false)
            assert(comments.isEmpty())
        }
    }


    @Test
    fun `fresh getUserById return data from remote data source`() {
        runBlocking {
            //given
            val answer = TestData.getDefaultUser();

            given(dataSourceRemote.getUserById(1))
                .willReturn(answer)
            given(dataSourceCache.getUserById(1))
                .willReturn(mapper.toDomain(UserDto()))

            // when
            val user = cut.getUserById(1,true)

            assertEquals(answer, user)
        }
    }

    @Test
    fun `getUserById return non-empty user when user cache is not empty`() {
        runBlocking {
            //given
            val answer = TestData.getDefaultUser();

            given(dataSourceRemote.getUserById(1))
                .willReturn(mapper.toDomain(UserDto()))
            given(dataSourceCache.getUserById(1))
                .willReturn(answer)

            // when
            val user = cut.getUserById(1,false)
            assertEquals(answer, user)
        }
    }




}