package com.r7fx.farmagitechs.data.api.response

import com.google.gson.annotations.SerializedName
import com.r7fx.farmagitechs.data.api.response.base.ResponseWrapper
import com.r7fx.farmagitechs.data.model.DataPatient
import retrofit2.Response

data class PatientListResult(
    @SerializedName("data")
    val data: List<DataPatient>
)

typealias PatientListResponse = Response<ResponseWrapper<PatientListResult>>