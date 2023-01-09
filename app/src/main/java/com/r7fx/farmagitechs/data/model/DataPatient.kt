package com.r7fx.farmagitechs.data.model


import com.google.gson.annotations.SerializedName
import com.r7fx.farmagitechs.domain.model.Gender
import com.r7fx.farmagitechs.domain.model.Patient
import java.util.Date

data class DataPatient(
    @SerializedName("patient_id")
    val patientId: String,
    @SerializedName("patient_name")
    val patientName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("date_birth")
    val dateBirth: Long,
    @SerializedName("address")
    val address: String
)