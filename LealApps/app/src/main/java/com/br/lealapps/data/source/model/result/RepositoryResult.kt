package com.br.lealapps.data.source.model.result

sealed class RepositoryResult<out T> {
    data class Success<out T>(val data: T) : RepositoryResult<T>()
    data class Error(val exception: Exception) : RepositoryResult<Nothing>()
}
