package com.r7fx.farmagitechs.data.api.response.base

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName("metaData")
    val metadata: Metadata,
    @SerializedName("response")
    val response: T?
)
