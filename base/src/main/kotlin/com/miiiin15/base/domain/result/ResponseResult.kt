package com.miiiin15.base.domain.result

sealed interface ResponseResult<out T> {
    data class Success<T>(val value: T) : ResponseResult<T>
    data class Failure(val throwable: Throwable? = null) : ResponseResult<Nothing>
}
