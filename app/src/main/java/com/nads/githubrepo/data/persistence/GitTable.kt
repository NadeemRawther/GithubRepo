package com.nads.githubrepo.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nads.githubrepo.data.models.BuiltBy

@Entity(tableName = "gitTable")
data class GitTable (@ColumnInfo(name ="author")
                       val author: String,
                     @ColumnInfo(name="avatar")
                       val avatar: String,
//                     @ColumnInfo(name="builtBy")
//                       val builtBy: List<BuiltBy>,
                     @ColumnInfo(name="currentPeriodStars")
                       val currentPeriodStars: Int,
                     @ColumnInfo(name="description")
                       val description: String,
                     @ColumnInfo(name="forks")
                       val forks: Int,
                     @ColumnInfo(name="language")
                       val language: String,
                     @ColumnInfo(name="languageColor")
                       val languageColor: String,
                     @ColumnInfo(name="name")
                       val name: String,
                     @ColumnInfo(name="stars")
                       val stars: Int,
                     @PrimaryKey(autoGenerate = false) @ColumnInfo(name="url")
                       val url: String)