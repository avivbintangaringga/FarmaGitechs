package com.r7fx.farmagitechs.domain.usecase

import com.r7fx.farmagitechs.domain.base.BaseUseCase
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.repository.MainRepository
import javax.inject.Inject

class GetPatientDetailsUseCase @Inject constructor(
    val repository: MainRepository
) : BaseUseCase<GetPatientDetailsUseCase.Params, Result<Patient>>() {
    data class Params(
        val patientId: String
    )

    override suspend fun implements(params: Params) =
        repository.getPatientDetails(params.patientId)
}