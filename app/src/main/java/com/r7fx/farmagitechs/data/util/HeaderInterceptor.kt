package com.r7fx.farmagitechs.data.util

import com.r7fx.farmagitechs.common.utils.Preferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val prefs: Preferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer ${prefs.token}")
                .build()
        )
    }
}