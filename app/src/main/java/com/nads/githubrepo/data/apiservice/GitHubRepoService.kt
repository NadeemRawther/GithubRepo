package com.nads.githubrepo.data.apiservice

import com.nads.githubrepo.data.models.GitItem
import com.nads.githubrepo.data.persistence.GitTable
import com.nads.githubrepo.util.Results
import retrofit2.http.GET


interface GitHubRepoService {
    @GET("testing")
    suspend fun getGitHubRepo(): List<GitTable>
}