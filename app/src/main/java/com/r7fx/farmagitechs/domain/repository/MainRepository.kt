package com.r7fx.farmagitechs.domain.repository

import com.r7fx.farmagitechs.domain.model.Document
import com.r7fx.farmagitechs.domain.model.Patient
import com.r7fx.farmagitechs.domain.base.Result
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun login(username: String, password: String): Result<String>

    suspend fun getPatientList(key: String = "", page: Int = 1): Result<List<Patient>>

    suspend fun getPatientDetails(patientId: String): Result<Patient>

    suspend fun getDocumentList(patientId: String): Result<List<Document>>

    suspend fun getDocument(documentId: Int): Result<String>
}