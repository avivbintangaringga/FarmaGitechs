package com.r7fx.farmagitechs.domain.usecase

import com.r7fx.farmagitechs.domain.base.BaseUseCase
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.repository.MainRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val repository: MainRepository
) : BaseUseCase<LoginUseCase.Params, Result<String>>() {
    data class Params(
        val username: String,
        val password: String
    )

    override suspend fun implements(params: Params) =
        repository.login(params.username, params.password)
}