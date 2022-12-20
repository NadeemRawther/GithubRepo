package com.nads.githubrepo.data.persistence


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nads.githubrepo.data.models.Converters

@Database(entities = [GitTable::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GitHubDatabase : RoomDatabase() {
       abstract fun gitHubDao():GitHubDao
}