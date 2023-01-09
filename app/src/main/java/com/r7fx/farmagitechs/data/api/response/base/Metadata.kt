package com.r7fx.farmagitechs.data.api.response.base

import com.google.gson.annotations.SerializedName

data class Metadata (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: List<String>
)