package com.uklontest.data.cache

import com.uklontest.data.TestData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DataSourceCacheImplTest {

    private lateinit var cut: DataSourceCacheImpl

    @Before
    fun setUp() {
        cut = DataSourceCacheImpl()
    }

    @Test
    fun `getPosts return not null data after saving`() {
        runBlocking {
            cut.savePosts(TestData.getNotEmptyPostList())
            assert(cut.getPosts().isNotEmpty())
        }
    }

    @Test
    fun `getCommentsForPost return not null data for ID after saving`() {
        runBlocking {
            cut.saveComments(1, TestData.getNotEmptyCommentList())
            assert(cut.getCommentsForPost(1).isNotEmpty())
            assert(cut.getCommentsForPost(2).isEmpty())
        }
    }

    @Test
    fun `getUserById return not null data for ID after saving`() {
        runBlocking {
            cut.saveUser(TestData.getDefaultUser())
            assertEquals(cut.getUserById(1), TestData.getDefaultUser())
            assertNull(cut.getUserById(2))
        }
    }

}