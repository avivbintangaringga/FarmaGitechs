package com.r7fx.farmagitechs.domain.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Document(
    val id: Int,
    val date: Date,
    val title: String,
    val fileName: String,
    val thumbnailUrl: String,
    val note: String,
)
