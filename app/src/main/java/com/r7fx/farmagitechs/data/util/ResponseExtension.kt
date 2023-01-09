package com.r7fx.farmagitechs.data.util

import com.r7fx.farmagitechs.data.api.response.base.ResponseWrapper
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.model.Patient
import retrofit2.Response

suspend fun <T : ResponseWrapper<*>> Response<T>.onError(
    onResult: suspend (message: String, code: Int) -> Unit
): Response<T> {
    if (body() == null || !isSuccessful) {
        onResult(message(), code())
    } else {
        body()?.let {
            if (it.metadata.code != 200) {
                onResult(it.metadata.message[0], it.metadata.code)
            }
        }
    }

    return this
}

suspend fun <R, T : ResponseWrapper<R>> Response<T>.onSuccess(
    onResult: suspend (data: R?) -> Unit
): Response<T> {
    body()?.let {
        onResult(it.response)
    }

    return this
}

fun <R, T : ResponseWrapper<R>> Response<T>.result(): Result<R> {
    body()?.let {
        if (it.metadata.code == 200)
            return Result.success(it.response!!)
        else
            return Result.error(it.metadata.message[0])
    } ?: return Result.error(message())
}