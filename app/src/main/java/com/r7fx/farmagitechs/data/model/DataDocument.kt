package com.r7fx.farmagitechs.data.model


import com.google.gson.annotations.SerializedName
import com.r7fx.farmagitechs.domain.model.Document
import java.util.Date

data class DataDocument(
    @SerializedName("patient_document_id")
    val patientDocumentId: Int,
    @SerializedName("patient_id")
    val patientId: String,
    @SerializedName("document_code")
    val documentCode: String,
    @SerializedName("created_time")
    val createdTime: Long,
    @SerializedName("modified_time")
    val modifiedTime: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("filename")
    val filename: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user")
    val user: DataUser,
    @SerializedName("deleted")
    val deleted: Int
)