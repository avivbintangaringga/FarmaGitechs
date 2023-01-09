package com.r7fx.farmagitechs.data.util

import com.r7fx.farmagitechs.data.model.DataDocument
import com.r7fx.farmagitechs.data.model.DataPatient
import com.r7fx.farmagitechs.domain.model.Document
import com.r7fx.farmagitechs.domain.model.Gender
import com.r7fx.farmagitechs.domain.model.Patient
import java.util.Date

fun DataDocument.toDomain() = Document(
    patientDocumentId,
    Date(modifiedTime),
    title,
    filename,
    "https://androidtest.farmagitechs.co.id/api/patient_document/serve_doc_thumbnail/$patientDocumentId",
    note
)

fun DataPatient.toDomain() = Patient(
    patientId,
    patientName,
    if(gender == "P") Gender.FEMALE else Gender.MALE,
    Date(dateBirth),
    address
)