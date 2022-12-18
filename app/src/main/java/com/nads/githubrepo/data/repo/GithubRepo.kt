package com.nads.githubrepo.data.repo

import com.nads.githubrepo.data.models.GitItem


interface GithubRepo {
    suspend fun getGitItemList(): Result<List<GitItem>>
}