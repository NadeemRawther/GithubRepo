package com.nads.githubrepo.data.models


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.nads.githubrepo.data.models.BuiltBy

@Entity
data class GitItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("builtBy")
    val builtBy: List<BuiltBy>,
    @SerializedName("currentPeriodStars")
    val currentPeriodStars: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("languageColor")
    val languageColor: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("url")
    val url: String
)