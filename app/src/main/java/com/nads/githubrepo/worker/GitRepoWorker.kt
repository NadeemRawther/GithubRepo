package com.nads.githubrepo.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitRepoWorker(context: Context,workerParameters: WorkerParameters)
    : CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
        Result.success()
    }


}