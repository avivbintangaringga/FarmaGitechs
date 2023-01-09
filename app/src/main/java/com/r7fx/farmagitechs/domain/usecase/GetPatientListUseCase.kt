package com.r7fx.farmagitechs.domain.usecase

import com.r7fx.farmagitechs.domain.base.BaseUseCase
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.repository.MainRepository
import javax.inject.Inject

class GetPatientListUseCase @Inject constructor(
    val repository: MainRepository
) : BaseUseCase<GetPatientListUseCase.Params, Result<List<Patient>>>() {
    data class Params(
        val key: String = "",
        val page: Int = 1
    )

    override suspend fun implements(params: Params) =
        repository.getPatientList(params.key, params.page)
}