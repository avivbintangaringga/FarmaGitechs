package com.r7fx.farmagitechs.ui.base

import com.r7fx.farmagitechs.domain.base.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface UseCaseRunner {
    val coroutineScope: CoroutineScope

    fun <T> runUseCase(
        onLoading: ((Boolean) -> Unit)? = null,
        onError: ((message: String) -> Unit)? = null,
        onComplete: ((data: T) -> Unit)? = null,
        block: (suspend () -> Result<T>)
    ) {
        coroutineScope.launch {
            onLoading?.invoke(true)
            try {
                val result = block()
                when(result.status) {
                    Result.Status.SUCCESS -> onComplete?.invoke(result.data!!)
                    Result.Status.ERROR -> onError?.invoke(result.message!!)
                }
            } catch (e: Exception) {
                onError?.invoke(e.message ?: "Unknown Error")
            } finally {
                onLoading?.invoke(false)
            }
        }
    }
}