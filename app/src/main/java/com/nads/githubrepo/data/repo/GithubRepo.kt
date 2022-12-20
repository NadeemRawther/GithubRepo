package com.nads.githubrepo.data.repo

import com.nads.githubrepo.data.models.GitItem
import com.nads.githubrepo.data.persistence.GitTable


interface GithubRepo {
    suspend fun getGitItemList(isOnline:Boolean): Result<List<GitTable>?>
}