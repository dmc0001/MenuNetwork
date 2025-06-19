package com.example.menunetwork.core.networking.utils

sealed interface ApiResults<out D, out E: ErrorResult> {
    data class Success<out D>(val data: D): ApiResults<D, Nothing>
    data class Error<out E: ErrorResult>(val error: E): ApiResults<Nothing, E>
}


inline fun <T, E: ErrorResult, R> ApiResults<T, E>.map(map: (T) -> R): ApiResults<R, E> {
    return when(this) {
        is ApiResults.Error -> ApiResults.Error(error)
        is ApiResults.Success -> ApiResults.Success(map(data))
    }
}


fun <T, E: ErrorResult> ApiResults<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}


inline fun <T, E: ErrorResult> ApiResults<T, E>.onSuccess(action: (T) -> Unit): ApiResults<T, E> {
    return when(this) {
        is ApiResults.Error -> this
        is ApiResults.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E: ErrorResult> ApiResults<T, E>.onError(action: (E) -> Unit): ApiResults<T, E> {
    return when(this) {
        is ApiResults.Error -> {
            action(error)
            this
        }
        is ApiResults.Success -> this
    }
}


typealias EmptyResult<E> = ApiResults<Unit, E>