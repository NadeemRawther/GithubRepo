package com.nads.githubrepo.data.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nads.githubrepo.data.persistence.GitHubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubRepoTestModule {


    @Provides
    @Named("test.db")
    fun provideDataBase(@ApplicationContext context: Context): GitHubDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            GitHubDatabase::class.java
        ).allowMainThreadQueries().build()
    }



}