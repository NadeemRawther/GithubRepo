package com.nads.githubrepo.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nads.githubrepo.data.models.GitItem
import retrofit2.HttpException
import java.io.IOException


class GitItemPagingRepo: PagingSource<Int, GitItem>() {
    override fun getRefreshKey(state: PagingState<Int, GitItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitItem> {
        TODO("Not yet implemented")
    }


}