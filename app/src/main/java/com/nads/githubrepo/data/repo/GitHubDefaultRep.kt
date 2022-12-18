package com.nads.githubrepo.data.repo

import com.nads.githubrepo.data.apiservice.GitHubRepoService
import com.nads.githubrepo.data.models.GitItem

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubDefaultRep @Inject constructor(
    private val gitHubRepoService: GitHubRepoService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):GithubRepo {

    override suspend fun getGitItemList(): Result<List<GitItem>> {
        return try {
            Result.success(gitHubRepoService.getGitHubRepo())
        } catch (e:Exception){
            Result.failure(e)
        }
    }


}