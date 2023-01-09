package com.r7fx.farmagitechs.data.api.response

import com.google.gson.annotations.SerializedName
import com.r7fx.farmagitechs.data.api.response.base.ResponseWrapper
import com.r7fx.farmagitechs.data.model.DataDocument
import retrofit2.Response

data class DocumentListResult(
    @SerializedName("data")
    val data: List<DataDocument>
)

typealias DocumentListResponse = Response<ResponseWrapper<DocumentListResult>>