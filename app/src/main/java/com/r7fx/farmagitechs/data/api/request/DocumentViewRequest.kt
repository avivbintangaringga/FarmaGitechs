package com.r7fx.farmagitechs.data.api.request


import com.google.gson.annotations.SerializedName

data class DocumentViewRequest(
    @SerializedName("patient_document_id")
    val patientDocumentId: Int
)