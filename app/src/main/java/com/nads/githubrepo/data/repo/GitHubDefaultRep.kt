package com.nads.githubrepo.data.repo



import android.util.Log
import com.nads.githubrepo.data.apiservice.GitHubRepoService
import com.nads.githubrepo.data.persistence.GitHubDao
import com.nads.githubrepo.data.persistence.GitTable
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubDefaultRep @Inject constructor(
    private val gitHubRepoService: GitHubRepoService,
    private val gitHubDao: GitHubDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):GithubRepo {

    override suspend fun getGitItemList(isOnline:Boolean): Result<List<GitTable>?> {

        if (isOnline){
            val data  =  gitHubRepoService.getGitHubRepo()
                gitHubDao.deleteAll()
                data.map {
                gitHubDao.insert(it)
                    Log.e("GYTU",it.author.toString())
            }
            return try {
              Result.success(data)
            } catch (e:Exception){
                Result.failure(e)
            }
        }else{
            val data = gitHubDao.getAll()

            return try {
                Result.success(data)
            }catch (e:Exception){
                Log.e("GYTU",e.toString())
                Result.failure(e)
            }
        }

    }


}