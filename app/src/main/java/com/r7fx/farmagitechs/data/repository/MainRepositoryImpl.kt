package com.r7fx.farmagitechs.data.repository

import android.content.Context
import com.r7fx.farmagitechs.data.api.request.DocumentListRequest
import com.r7fx.farmagitechs.data.api.request.DocumentViewRequest
import com.r7fx.farmagitechs.data.api.request.LoginRequest
import com.r7fx.farmagitechs.data.api.request.PatientDetailsRequest
import com.r7fx.farmagitechs.data.api.request.PatientListRequest
import com.r7fx.farmagitechs.data.api.services.ApiService
import com.r7fx.farmagitechs.data.util.result
import com.r7fx.farmagitechs.data.util.toDomain
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.repository.MainRepository
import com.r7fx.farmagitechs.common.utils.Preferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val preferences: Preferences,
    @ApplicationContext val context: Context
) : MainRepository {
    override suspend fun login(username: String, password: String) =
        apiService
            .login(LoginRequest(username, password))
            .result()
            .let {
                return@let when (it.status) {
                    Result.Status.SUCCESS -> {
                        preferences.token = it.data!!.token
                        Result.success(it.data.token)
                    }

                    Result.Status.ERROR -> Result.error(it.message!!)
                }
            }

    override suspend fun getPatientList(key: String, page: Int) =
        apiService
            .patientList(page, PatientListRequest(key = key))
            .result()
            .let {
                return@let when (it.status) {
                    Result.Status.SUCCESS -> {
                        Result.success(it.data!!.data.map {
                            it.toDomain()
                        })
                    }

                    Result.Status.ERROR -> Result.error(it.message!!)
                }
            }

    override suspend fun getPatientDetails(patientId: String) =
        apiService
            .patientDetails(PatientDetailsRequest(patientId))
            .result()
            .let {
                return@let when (it.status) {
                    Result.Status.SUCCESS -> {
                        Result.success(it.data!!.data.toDomain())
                    }

                    Result.Status.ERROR -> Result.error(it.message!!)
                }
            }

    override suspend fun getDocumentList(patientId: String) =
        apiService
            .documentList(DocumentListRequest(patientId))
            .result()
            .let {
                return@let when (it.status) {
                    Result.Status.SUCCESS -> {
                        Result.success(it.data!!.data.map {
                            it.toDomain()
                        })
                    }

                    Result.Status.ERROR -> Result.error(it.message!!)
                }
            }

    override suspend fun getDocument(documentId: Int): Result<String> {
        val cacheDir = context.cacheDir.absolutePath
        val docsDir = File("$cacheDir/docs")
        docsDir.mkdirs()
        val filePath = docsDir.absolutePath + "/$documentId.pdf"
        val file = File(filePath)

        if (file.exists()) return Result.success(file.absolutePath)

        val response = apiService
            .documentView(DocumentViewRequest(documentId))

        if (response.isSuccessful) {
            response.body()?.let {

                if (!file.exists())
                    it.byteStream().use { input ->
                        file.outputStream()
                            .use { output ->
                                output.flush()
                                input.copyTo(output)
                            }
                    }

                return Result.success(file.absolutePath)
            } ?: return Result.error(response.message())
        } else {
            return Result.error(response.message())
        }
    }
}