package com.testtask.data.cache

import com.testtask.data.TestData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
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
            assertNotNull(cut.getPosts())
        }
    }

    @Test
    fun `getCommentsForPost return not null data for ID after saving`() {
        runBlocking {
            cut.saveComments(1, TestData.getNotEmptyCommentList())
            assertNotNull(cut.getCommentsForPost(1))
            assertNull(cut.getCommentsForPost(2))
        }
    }

    @Test
    fun `getUserById return not null data for ID after saving`() {
        runBlocking {
            cut.saveUser(TestData.getDefaultUser())
            assertEquals(cut.getUserById(1), TestData.getDefaultUser())
        }
    }

    @Test
    fun `getUserById return null data for incorrect user ID`() {
        runBlocking {
            cut.saveUser(TestData.getDefaultUser())
            assertNull(cut.getUserById(2))
        }
    }

}