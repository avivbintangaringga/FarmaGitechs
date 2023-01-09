package com.r7fx.farmagitechs.domain.usecase

import com.r7fx.farmagitechs.domain.base.BaseUseCase
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.model.Document
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.repository.MainRepository
import javax.inject.Inject

class GetDocumentListUseCase @Inject constructor(
    val repository: MainRepository
) : BaseUseCase<GetDocumentListUseCase.Params, Result<List<Document>>>() {
    data class Params(
        val patientId: String
    )

    override suspend fun implements(params: Params) =
        repository.getDocumentList(params.patientId)
}