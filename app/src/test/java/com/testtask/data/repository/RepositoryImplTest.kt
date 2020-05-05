package com.testtask.data.repository

import com.nhaarman.mockitokotlin2.given
import com.testtask.data.TestData
import com.testtask.data.cache.DataSourceCacheImpl
import com.testtask.data.mapper.DataMapper
import com.testtask.data.remote.DataSourceRemoteImpl
import junit.framework.TestCase.*
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

            val answer = TestData.getNotEmptyPostList()

            given(dataSourceRemote.getPosts())
                .willReturn(answer)
            given(dataSourceCache.getPosts())
                .willReturn(null)

            // when
            val result = cut.getPosts(true)

            assertNotNull(result)
            assertEquals(answer, result)
        }
    }

    @Test
    fun `getPosts return non-empty list when cache is not empty`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyPostList()

            given(dataSourceRemote.getPosts())
                .willReturn(null)
            given(dataSourceCache.getPosts())
                .willReturn(answer)

            // when
            val posts = cut.getPosts(false)
            assertNotNull(posts)
            assertEquals(answer, posts)
        }
    }

    @Test
    fun `getPosts return empty list when cache and remote are empty`() {
        runBlocking {
            //given
            given(dataSourceRemote.getPosts())
                .willReturn(null)
            given(dataSourceCache.getPosts())
                .willReturn(null)

            // when
            val posts = cut.getPosts(false)
            assertNull(posts)
        }
    }

    @Test
    fun `fresh getCommentsForPost return data from remote data source`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyCommentList()

            given(dataSourceRemote.getCommentsForPost(1))
                .willReturn(answer)
            given(dataSourceCache.getCommentsForPost(1))
                .willReturn(null)

            // when
            val comments = cut.getCommentsForPost(1,true)

            assertNotNull(comments)
            assertEquals(answer, comments)
        }
    }

    @Test
    fun `getCommentsForPost return non-empty list when cache is not empty`() {
        runBlocking {
            //given
            val answer = TestData.getNotEmptyCommentList()

            given(dataSourceRemote.getCommentsForPost(1))
                .willReturn(null)
            given(dataSourceCache.getCommentsForPost(1))
                .willReturn(answer)

            // when
            val comments = cut.getCommentsForPost(1,false)
            assertNotNull(comments)
            assertEquals(answer, comments)
        }
    }

    @Test
    fun `getCommentsForPost return null when cache and remote are empty`() {
        runBlocking {
            //given
            given(dataSourceRemote.getCommentsForPost(1))
                .willReturn(null)
            given(dataSourceCache.getCommentsForPost(1))
                .willReturn(null)

            // when
            val comments = cut.getCommentsForPost(1,false)
            assertNull(comments)
        }
    }


    @Test
    fun `fresh getUserById return data from remote data source`() {
        runBlocking {

            val answer = TestData.getDefaultUser()

            given(dataSourceRemote.getUserById(1))
                .willReturn(answer)
            given(dataSourceCache.getUserById(1))
                .willReturn(mapper.toDomain(TestData.getDefaultUserDto()))

            val user = cut.getUserById(1, true)

            assertEquals(answer, user)
        }
    }

    @Test
    fun `getUserById return non-empty user when user cache is not empty`() {
        runBlocking {
            given(dataSourceRemote.getUserById(1))
                .willReturn(mapper.toDomain(TestData.getDefaultUserDto()))
            given(dataSourceCache.getUserById(1))
                .willReturn(mapper.toDomain(TestData.getDefaultUserDto()))

            val user = cut.getUserById(1,false)
            assertNotNull(user)
        }
    }
}