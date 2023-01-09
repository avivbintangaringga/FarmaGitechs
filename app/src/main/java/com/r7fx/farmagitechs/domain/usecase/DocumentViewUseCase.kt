package com.r7fx.farmagitechs.domain.usecase

import com.r7fx.farmagitechs.domain.base.BaseUseCase
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.repository.MainRepository
import javax.inject.Inject

class DocumentViewUseCase @Inject constructor(
    val repository: MainRepository
) : BaseUseCase<DocumentViewUseCase.Params, Result<String>>() {
    data class Params(
        val documentId: Int
    )

    override suspend fun implements(params: Params) =
        repository.getDocument(params.documentId)
}