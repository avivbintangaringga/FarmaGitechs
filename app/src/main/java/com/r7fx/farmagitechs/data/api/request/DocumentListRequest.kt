package com.r7fx.farmagitechs.data.api.request


import com.google.gson.annotations.SerializedName

data class DocumentListRequest(
    @SerializedName("patient_id")
    val patientId: String
)