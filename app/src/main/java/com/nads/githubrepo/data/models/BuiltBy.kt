package com.nads.githubrepo.data.models


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class BuiltBy(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("username")
    val username: String
)