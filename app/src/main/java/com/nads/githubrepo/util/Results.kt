package com.nads.githubrepo.util

sealed class Results<out R> {
    object Loading : Results<Nothing>()
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()
}