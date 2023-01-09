package com.r7fx.farmagitechs.data.api.response

import com.google.gson.annotations.SerializedName
import com.r7fx.farmagitechs.data.api.response.base.ResponseWrapper
import retrofit2.Response

data class LoginResult(
    @SerializedName("token")
    val token: String
)

typealias LoginResponse = Response<ResponseWrapper<LoginResult>>