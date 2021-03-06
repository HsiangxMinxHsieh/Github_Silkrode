package com.timmy.github_silkrode.base

import com.timmy.github_silkrode.view.IViewState


sealed class Results<out T> : IViewState {

    companion object {
        fun <T> success(result: T): Results<T> = Success(result)
        fun <T> failure(error: Throwable): Results<T> = Failure(error)
    }

    data class Failure(val error: Throwable) : Results<Nothing>()
    data class Success<out T>(val data: T) : Results<T>()
}