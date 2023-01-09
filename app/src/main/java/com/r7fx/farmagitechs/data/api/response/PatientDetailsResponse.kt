package com.r7fx.farmagitechs.data.api.response

import com.google.gson.annotations.SerializedName
import com.r7fx.farmagitechs.data.api.response.base.ResponseWrapper
import com.r7fx.farmagitechs.data.model.DataPatient
import retrofit2.Response

data class PatientDetailsResult(
    @SerializedName("data")
    val data: DataPatient
)

typealias PatientDetailsResponse = Response<ResponseWrapper<PatientDetailsResult>>