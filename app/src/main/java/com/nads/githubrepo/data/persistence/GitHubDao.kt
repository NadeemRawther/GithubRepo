package com.nads.githubrepo.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nads.githubrepo.data.models.GitItem
import java.util.concurrent.Flow

@Dao
interface GitHubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(gitItem: GitTable)

    @Query("SELECT * FROM gitTable")
    fun getAll(): LiveData<List<GitTable>>

    @Query("DELETE FROM gitTable")
    suspend fun deleteAll()
}