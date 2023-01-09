package com.r7fx.farmagitechs.data.api.request


import com.google.gson.annotations.SerializedName

data class PatientListRequest(
    @SerializedName("key")
    val key: String = "",
    @SerializedName("patient_id")
    val patientId: String = "",
    @SerializedName("patient_name")
    val patientName: String = ""
)