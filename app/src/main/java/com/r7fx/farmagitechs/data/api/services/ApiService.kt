package com.r7fx.farmagitechs.data.api.services

import com.r7fx.farmagitechs.data.api.request.DocumentListRequest
import com.r7fx.farmagitechs.data.api.request.DocumentViewRequest
import com.r7fx.farmagitechs.data.api.request.LoginRequest
import com.r7fx.farmagitechs.data.api.request.PatientDetailsRequest
import com.r7fx.farmagitechs.data.api.request.PatientListRequest
import com.r7fx.farmagitechs.data.api.response.DocumentListResponse
import com.r7fx.farmagitechs.data.api.response.DocumentViewResponse
import com.r7fx.farmagitechs.data.api.response.LoginResponse
import com.r7fx.farmagitechs.data.api.response.PatientDetailsResponse
import com.r7fx.farmagitechs.data.api.response.PatientListResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/generate_token")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("patient/list/{page}")
    suspend fun patientList(@Path("page") page: Int, @Body request: PatientListRequest): PatientListResponse

    @POST("patient/get")
    suspend fun patientDetails(@Body request: PatientDetailsRequest): PatientDetailsResponse

    @POST("patient_document/list_doc")
    suspend fun documentList(@Body request: DocumentListRequest): DocumentListResponse

    @POST("patient_document/serve_doc")
    suspend fun documentView(@Body request: DocumentViewRequest): DocumentViewResponse
}