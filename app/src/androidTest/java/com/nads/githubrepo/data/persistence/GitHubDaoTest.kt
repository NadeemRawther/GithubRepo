package com.nads.githubrepo.data.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nads.githubrepo.data.models.BuiltBy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import com.nads.githubrepo.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class GitHubDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)
    @Inject
    @Named("test.db")
    lateinit var database: GitHubDatabase
    @Inject
    lateinit var dao: GitHubDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.gitHubDao()

    }
    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runTest {
        val gititem = listOf<GitTable>( GitTable("name", "sdf",
            builtBy = listOf(BuiltBy("ye","dfs","sdf"))
            , 5,"as we",785,"getviolet",
            "Red","nads",85,"fake"))
        gititem.map { dao.insert(it) }


        val allGitItems = dao.getAll().getOrAwaitValue()

        assertThat(allGitItems).isIn(gititem)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        val gititem = arrayListOf<GitTable>( GitTable("name", "sdf",
            builtBy = listOf(BuiltBy("ye","dfs","sdf"))
            , 5,"as we",785,"getviolet",
            "Red","nads",85,"fake"))
        gititem.map { dao.insert(it) }
        dao.deleteAll()

        val allGitItems = dao.getAll().getOrAwaitValue()

        assertThat(allGitItems).isNotIn(gititem)
    }

}