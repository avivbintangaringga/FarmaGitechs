package com.r7fx.farmagitechs.domain.base

data class Result<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T) = Result<T>(Status.SUCCESS, data)
        fun <T> error(message: String) = Result<T>(Status.ERROR, message = message)
    }
}