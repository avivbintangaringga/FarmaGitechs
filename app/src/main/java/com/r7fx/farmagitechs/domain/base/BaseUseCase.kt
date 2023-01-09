package com.r7fx.farmagitechs.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<P, R> {
    open val dispatcher = Dispatchers.IO

    abstract suspend fun implements(params: P): R

    suspend operator fun invoke(params: P) = withContext(dispatcher) {
        implements(params)
    }
}